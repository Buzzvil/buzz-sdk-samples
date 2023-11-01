import UIKit
import BuzzAdBenefitSDK

class CarouselCell: UICollectionViewCell {
  private lazy var nativeAd2View = BZVNativeAd2View(frame: .zero)
  private lazy var mediaView = BZVMediaView(frame: .zero)
  private lazy var iconImageView = UIImageView(frame: .zero)
  private lazy var titleLabel = UILabel(frame: .zero)
  private lazy var descriptionLabel = UILabel(frame: .zero)
  private lazy var ctaView = BZVDefaultCtaView(frame: .zero)
  
  // NativeAd2View와 하위 컴포넌트를 연결합니다.
  private lazy var viewBinder = BZVNativeAd2ViewBinder
    .Builder(unitId: "YOUR_NATIVE_UNIT_ID")
    .nativeAd2View(nativeAd2View)
    .mediaView(mediaView)
    .iconImageView(iconImageView)
    .titleLabel(titleLabel)
    .descriptionLabel(descriptionLabel)
    .ctaView(ctaView)
    .build()
  
  // 로딩 화면 구현하기
  private lazy var activityIndicatorView: UIActivityIndicatorView = {
      let indicator = UIActivityIndicatorView(frame: .zero)
      indicator.hidesWhenStopped = true
      return indicator
    }()
  
  override init(frame: CGRect) {
    super.init(frame: frame)
    setupView()
    setupLayout()
  }
  
  required init?(coder: NSCoder) {
    fatalError()
  }
  
  private func setupView() {
    contentView.addSubview(nativeAd2View)
    nativeAd2View.addSubview(mediaView)
    nativeAd2View.addSubview(iconImageView)
    nativeAd2View.addSubview(titleLabel)
    nativeAd2View.addSubview(descriptionLabel)
    nativeAd2View.addSubview(ctaView)
    
    // 로딩 화면 구현하기
    contentView.addSubview(activityIndicatorView)
  }
  
  private func setupLayout() {
    // eg. auto layout constraints for nativeAd2View
    nativeAd2View.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      nativeAd2View.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
      nativeAd2View.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: 16),
      nativeAd2View.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -16),
      nativeAd2View.bottomAnchor.constraint(equalTo: safeAreaLayoutGuide.bottomAnchor),
    ])
    
    // AutoLayout Constraints를 설정하세요.
    // ...
    
    // 로딩화면 구현하기
    activityIndicatorView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      activityIndicatorView.centerXAnchor.constraint(equalTo: centerXAnchor),
      activityIndicatorView.centerYAnchor.constraint(equalTo: centerYAnchor),
    ])
  }
  
  // 앞뒤 광고 아이템을 부분적으로 노출하기
//  private func setupLayout() {
//    // CarouselCell과 동일한 크기로 설정합니다.
//    nativeAd2View.translatesAutoresizingMaskIntoConstraints = false
//    NSLayoutConstraint.activate([
//      nativeAd2View.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
//      nativeAd2View.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor),
//      nativeAd2View.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor),
//      nativeAd2View.bottomAnchor.constraint(equalTo: safeAreaLayoutGuide.bottomAnchor),
//    ])
//
//    // ...
//  }
  
  override func prepareForReuse() {
    super.prepareForReuse()
    // prepareForReuse 내에서 unbind를 반드시 호출하여 cell을 재사용할 때 문제가 발생하지 않게 합니다.
    viewBinder.unbind()
  }
  
  // collectionView의 collectionView(_:cellForItemAt:) 시점에 호출합니다.
  func setPool(with pool: BZVNativeAd2Pool, for adKey: Int) {
    // 해당 index(adKey)에 해당하는 NativeAd2ViewBinder가 NativeAd2Pool을 사용하도록 합니다.
    viewBinder.setPool(pool, at: adKey)
  }
  
  // collectionView의 collectionView(_:cellForItemAt:) 시점에 호출합니다.
  func bind() {
    // NativeAd2ViewBinder의 bind()를 호출하면 광고 할당 및 갱신이 자동으로 수행됩니다.
    viewBinder.bind()
  }
  
  // 로딩화면 구현하기
  func setupLoading() {
    viewBinder.subscribeEvents(onRequest: { [weak self] in
      self?.activityIndicatorView.startAnimating()
      self?.nativeAd2View.alpha = 0.5
    }, onNext: { [weak self] _ in
      self?.activityIndicatorView.stopAnimating()
      self?.nativeAd2View.alpha = 1
    }, onError: { [weak self] in
      self?.activityIndicatorView.stopAnimating()
      self?.nativeAd2View.alpha = 1
      print("error: \($0)")
    }, onCompleted: { [weak self] in
      self?.activityIndicatorView.stopAnimating()
      self?.nativeAd2View.alpha = 1
      print("completed")
    })
  }
  
  // 광고 이벤트 리스너 등록하기
  func setupEventListeners() {
    viewBinder.subscribeAdEvents(onImpressed: { [weak self] in
      print("impressed: \($0.title)")
    }, onClicked: { [weak self] in
      print("clicked: \($0.title)")
    }, onRewardRequested: { [weak self] in
      print("requested reward: \($0.title)")
    }, onRewarded: { [weak self] in
      print("received reward result: \($0.title), \($1)")
    }, onParticipated: { [weak self] in
      print("participated: \($0.title)")
    })
  }
}
