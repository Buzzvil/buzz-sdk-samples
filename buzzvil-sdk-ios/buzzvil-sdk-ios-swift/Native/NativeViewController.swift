import BuzzvilSDK
import UIKit

class NativeViewController: UIViewController {
  private let nativeAd2View = BZVNativeAd2View(frame: .zero)
  private let mediaView = BZVMediaView(frame: .zero)
  private let iconImageView = UIImageView(frame: .zero)
  private let titleLabel = UILabel(frame: .zero)
  private let descriptionLabel = UILabel(frame: .zero)
  private let ctaView = BZVDefaultCtaView(frame: .zero)
  private lazy var viewBinder = BZVNativeAd2ViewBinder
    .Builder(unitId: "YOUR_NATIVE_UNIT_ID")
    .nativeAd2View(nativeAd2View)
    .mediaView(mediaView)
    .iconImageView(iconImageView)
    .titleLabel(titleLabel)
    .descriptionLabel(descriptionLabel)
    .ctaView(ctaView)
    .build()
  
  private let activityIndicatorView = UIActivityIndicatorView(frame: .zero)
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    setupView()
    setupLayout()
    nativeAdLoad()
  }
  
  private func setupView() {
    // 광고 레이아웃 구성하기
    self.view.addSubview(nativeAd2View)
    nativeAd2View.addSubview(mediaView)
    nativeAd2View.addSubview(iconImageView)
    nativeAd2View.addSubview(titleLabel)
    nativeAd2View.addSubview(descriptionLabel)
    nativeAd2View.addSubview(ctaView)
  }
  
  private func setupLayout() {
    // AutoLayout Constraints 설정
    nativeAd2View.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      nativeAd2View.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 16),
      nativeAd2View.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -16),
      nativeAd2View.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 8)
    ])
    
    mediaView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      mediaView.topAnchor.constraint(equalTo: nativeAd2View.topAnchor),
      mediaView.leadingAnchor.constraint(equalTo: nativeAd2View.leadingAnchor),
      mediaView.trailingAnchor.constraint(equalTo: nativeAd2View.trailingAnchor),
      mediaView.heightAnchor.constraint(equalTo: mediaView.widthAnchor, multiplier: 627/1200),
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
      titleLabel.trailingAnchor.constraint(equalTo: nativeAd2View.trailingAnchor, constant: -8)
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
      ctaView.trailingAnchor.constraint(equalTo: nativeAd2View.trailingAnchor, constant: -8),
      ctaView.bottomAnchor.constraint(equalTo: nativeAd2View.bottomAnchor, constant: -8),
      ctaView.heightAnchor.constraint(equalToConstant: 32)
    ])
  }
  
  private func nativeAdLoad() {
    // 광고 보여주기 & 광고 이벤트 리스너 등록하기
    // Warning: retain cycle 방지를 위해 closure 내에서 반드시 weak self를 사용해주세요.
    viewBinder.subscribeEvents(onRequest: { [weak self] in
      // 광고 할당을 요청한 상태입니다.
      // 이후에는 onNext, onCompleted, onError 중 하나가 호출됩니다.
      // 광고 자동 갱신을 시도할 때마다 반복적으로 호출됩니다.
      // 로딩 화면 등을 구현할 수 있습니다.
      self?.activityIndicatorView.startAnimating()
    }, onNext: { [weak self] nativeAd2 in
      // 광고 할당에 성공하면 호출됩니다.
      // 이후에 광고 갱신 시 onRequest가 다시 호출됩니다.
      // 광고 자동 갱신을 성공할 때마다 반복적으로 호출됩니다.
      // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
      self?.activityIndicatorView.stopAnimating()
    }, onError: { [weak self] error in
      // 최초 광고 할당에 실패하면 호출됩니다.
      // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
      self?.activityIndicatorView.stopAnimating()
      // NativeAd2View를 숨기거나, Error UI로 대체할 수 있습니다.
      print("Failed to load ad by \(error.localizedDescription).")
    }, onCompleted: { [weak self] in
      // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
      // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
      self?.activityIndicatorView.stopAnimating()
    })
    
    // 광고 할당 및 표시를 자동으로 수행합니다.
    viewBinder.bind()
    
    // 동영상 광고 리스너 등록하기
    nativeAd2View.videoDelegate = self
  }
}

// 동영상 광고 리스너 등록하기
// MARK: BZVNativeAdViewVideoDelegate
extension NativeViewController: BZVNativeAdViewVideoDelegate {
  func bzvNativeAdView(_ nativeAdView: BZVNativeAdView, willStartPlayingVideoAd nativeAd: BZVNativeAd) {
    // 비디오 광고의 비디오가 시작하기 직전에 호출됩니다.
  }

  func bzvNativeAdView(_ nativeAdView: BZVNativeAdView, didResumeVideoAd nativeAd: BZVNativeAd) {
    // 비디오 광고의 비디오가 재생되면 호출됩니다.
  }

  func bzvNativeAdView(_ nativeAdView: BZVNativeAdView, willReplayVideoAd nativeAd: BZVNativeAd) {
    // 비디오 광고의 비디오가 리플레이되면 호출됩니다.
  }

  func bzvNativeAdView(_ nativeAdView: BZVNativeAdView, didPauseVideoAd nativeAd: BZVNativeAd) {
    // 비디오 광고의 비디오가 일시정지되면 호출됩니다.
  }

  func bzvNativeAdView(_ nativeAdView: BZVNativeAdView, didFinishPlayingVideoAd nativeAd: BZVNativeAd) {
    // 비디오 광고의 비디오가 종료되면 호출됩니다.
  }
}
