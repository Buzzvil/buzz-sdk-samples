import UIKit
import BuzzvilSDK

final class CarouselCell: UICollectionViewCell {
  private lazy var nativeAdView = BuzzNativeAdView(frame: .zero)
  private lazy var mediaView = BuzzMediaView(frame: .zero)
  private lazy var iconImageView = UIImageView(frame: .zero)
  private lazy var titleLabel = UILabel(frame: .zero)
  private lazy var descriptionLabel = UILabel(frame: .zero)
  private lazy var ctaView = BuzzDefaultCtaView(frame: .zero)
  
  // NativeAdView와 하위 컴포넌트를 연결합니다.
  private lazy var viewBinder = BuzzNativeViewBinder.Builder()
    .nativeAdView(nativeAdView)
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
    contentView.addSubview(nativeAdView)
    nativeAdView.addSubview(mediaView)
    nativeAdView.addSubview(iconImageView)
    nativeAdView.addSubview(titleLabel)
    nativeAdView.addSubview(descriptionLabel)
    nativeAdView.addSubview(ctaView)
    
    // 로딩 화면 구현하기
    contentView.addSubview(activityIndicatorView)
  }
  
  private func setupLayout() {
    // eg. auto layout constraints for nativeAdView
    nativeAdView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      nativeAdView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: 16),
      nativeAdView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -16),
      nativeAdView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor, constant: 8)
    ])
    
    mediaView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      mediaView.topAnchor.constraint(equalTo: nativeAdView.topAnchor),
      mediaView.leadingAnchor.constraint(equalTo: nativeAdView.leadingAnchor),
      mediaView.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor),
      mediaView.heightAnchor.constraint(equalTo: mediaView.widthAnchor, multiplier: 627.0/1200.0),
    ])
    
    iconImageView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      iconImageView.topAnchor.constraint(equalTo: mediaView.bottomAnchor, constant: 8),
      iconImageView.leadingAnchor.constraint(equalTo: mediaView.leadingAnchor, constant: 8),
      iconImageView.heightAnchor.constraint(equalToConstant: 32),
      iconImageView.widthAnchor.constraint(equalToConstant: 32)
    ])
    
    titleLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      titleLabel.centerYAnchor.constraint(equalTo: iconImageView.centerYAnchor),
      titleLabel.leadingAnchor.constraint(equalTo: iconImageView.trailingAnchor, constant: 8),
      titleLabel.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor, constant: -8)
    ])
    
    descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      descriptionLabel.topAnchor.constraint(equalTo: iconImageView.bottomAnchor, constant: 8),
      descriptionLabel.leadingAnchor.constraint(equalTo: iconImageView.leadingAnchor),
      descriptionLabel.trailingAnchor.constraint(equalTo: titleLabel.trailingAnchor)
    ])
    
    ctaView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      ctaView.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 8),
      ctaView.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor, constant: -8),
      ctaView.bottomAnchor.constraint(equalTo: nativeAdView.bottomAnchor, constant: -8),
      ctaView.heightAnchor.constraint(equalToConstant: 36)
    ])
    
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
  func bind(with native: BuzzNative) {
    // 로딩화면 구현하기
    native.subscribeRefreshEvents(
      onRequest: { [weak self] in
        self?.activityIndicatorView.startAnimating()
        self?.nativeAdView.alpha = 0.5
      },
      onSuccess: { [weak self] _ in
        self?.activityIndicatorView.stopAnimating()
        self?.nativeAdView.alpha = 1
      },
      onFailure: { [weak self] error in
        self?.activityIndicatorView.stopAnimating()
        self?.nativeAdView.alpha = 1
      }
    )
    
    // 광고 이벤트 리스너 등록하기
    native.subscribeAdEvents(
      onImpressed: { [weak self] in
        print("impressed: \($0.title)")
      },
      onClicked: { [weak self] in
        print("clicked: \($0.title)")
      },
      onRewardRequested: { [weak self] in
        print("requested reward: \($0.title)")
      },
      onRewarded: { [weak self] in
        print("received reward result: \($0.title), \($1)")
      },
      onParticipated: { [weak self] in
        print("participated: \($0.title)")
      }
    )
    
    // bind를 호출하면 할당된 광고 표시 및 갱신이 자동으로 수행됩니다.
    viewBinder.bind(native)
  }
}
