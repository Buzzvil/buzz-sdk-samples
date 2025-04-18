import SwiftUI
import BuzzvilSDK

struct NativeView: UIViewControllerRepresentable {
  typealias UIViewControllerType = NativeViewController
  
  func makeUIViewController(context: Context) -> NativeViewController {
    return NativeViewController()
  }
  
  func updateUIViewController(_ uiViewController: NativeViewController, context: Context) {}
}

final class NativeViewController: UIViewController {
  private lazy var native = BuzzNative(unitId: "YOUR_NATIVE_UNIT_ID")
  
  private let nativeAdView = BuzzNativeAdView(frame: .zero)
  private let mediaView = BuzzMediaView(frame: .zero)
  private let iconImageView = UIImageView(frame: .zero)
  private let titleLabel = UILabel(frame: .zero)
  private let descriptionLabel = UILabel(frame: .zero)
  private let ctaView = BuzzDefaultCtaView(frame: .zero)
  
  private lazy var benefitHubEntryView: UIButton = {
    let button = UIButton(type: .system)
    button.setTitle("포인트 더 받으러 가기 >", for: .normal)
    button.addTarget(self, action: #selector(showBenefitHub), for: .touchUpInside)
    return button
  }()
  
  private lazy var viewBinder = BuzzNativeViewBinder.Builder()
    .nativeAdView(nativeAdView)
    .mediaView(mediaView)
    .iconImageView(iconImageView)
    .titleLabel(titleLabel)
    .descriptionLabel(descriptionLabel)
    .ctaView(ctaView)
    .build()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    setupView()
    setupLayout()
    loadNativeAd()
  }
  
  private func setupView() {
    view.backgroundColor = .systemBackground
    
    view.addSubview(benefitHubEntryView)
    
    mediaView.layer.cornerRadius = 8
    iconImageView.layer.cornerRadius = 8
    iconImageView.clipsToBounds = true
    titleLabel.font = .systemFont(ofSize: 16, weight: .semibold)
    descriptionLabel.font = .systemFont(ofSize: 16, weight: .medium)
    
    // 광고 레이아웃 구성하기
    view.addSubview(nativeAdView)
    nativeAdView.addSubview(mediaView)
    nativeAdView.addSubview(iconImageView)
    nativeAdView.addSubview(titleLabel)
    nativeAdView.addSubview(descriptionLabel)
    nativeAdView.addSubview(ctaView)
  }
  
  private func setupLayout() {
    // AutoLayout Constraints 설정
    benefitHubEntryView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      benefitHubEntryView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 8),
      benefitHubEntryView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -16),
    ])
    
    nativeAdView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      nativeAdView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 16),
      nativeAdView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -16),
      nativeAdView.topAnchor.constraint(equalTo: benefitHubEntryView.bottomAnchor, constant: 8)
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
      iconImageView.widthAnchor.constraint(equalToConstant: 32),
    ])
    
    titleLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      titleLabel.centerYAnchor.constraint(equalTo: iconImageView.centerYAnchor),
      titleLabel.leadingAnchor.constraint(equalTo: iconImageView.trailingAnchor, constant: 8),
      titleLabel.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor, constant: -8),
    ])
    
    descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      descriptionLabel.topAnchor.constraint(equalTo: iconImageView.bottomAnchor, constant: 8),
      descriptionLabel.leadingAnchor.constraint(equalTo: iconImageView.leadingAnchor),
      descriptionLabel.trailingAnchor.constraint(equalTo: titleLabel.trailingAnchor),
    ])
    
    ctaView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      ctaView.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 8),
      ctaView.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor, constant: -8),
      ctaView.bottomAnchor.constraint(equalTo: nativeAdView.bottomAnchor, constant: -8),
      ctaView.heightAnchor.constraint(equalToConstant: 36)
    ])
  }
  
  private func loadNativeAd() {
    // 광고 갱신 이벤트 리스너 등록하기
    // Warning: retain cycle 방지를 위해 closure 내에서 반드시 weak self를 사용해주세요.
    native.subscribeRefreshEvents(
      onRequest: { [weak self] in
        // 광고 갱신 할당을 요청한 상태입니다.
        // 이후에는 onSuccess, onFailure 중 하나가 호출됩니다.
        // 광고 자동 갱신을 시도할 때마다 반복적으로 호출됩니다.
      },
      onSuccess: { [weak self] nativeAd in
        // 광고 갱신 할당에 성공하면 호출됩니다.
      },
      onFailure: { [weak self] error in
        // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
      }
    )
    
    // 광고 이벤트 리스너 등록하기
    // Warning: retain cycle 방지를 위해 closure 내에서 반드시 weak self를 사용해주세요.
    native.subscribeAdEvents(
      onImpressed: { [weak self] nativeAd in
        // 광고가 유저에게 노출되었을 때 호출됩니다.
      },
      onClicked: { [weak self] nativeAd in
        // 유저가 광고를 클릭했을 때 호출됩니다.
      },
      onRewardRequested: { [weak self] nativeAd in
        // 리워드 적립 요청시에 호출됩니다.
      },
      onRewarded: { [weak self] nativeAd, rewardResult in
        // 리워드 적립 결과를 수신했을 때 호출됩니다.
      },
      onParticipated: { [weak self] nativeAd in
        // 광고 참여가 완료되었을 때 호출됩니다.
      }
    )
    
    // 광고 할당 요청하기
    // Warning: retain cycle 방지를 위해 closure 내에서 반드시 weak self를 사용해주세요.
    native.load(
      onSuccess: { [weak self] nativeAd in
        // 초기 광고 할당에 성공하면 호출됩니다.
      },
      onFailure: { [weak self] error in
        // 초기 광고 할당에 실패하면 호출됩니다.
      }
    )
    
    // 할당된 광고 보여주기
    viewBinder.bind(native)
  }
  
  @objc
  private func showBenefitHub() {
    let benefitHub = BuzzBenefitHub()
    benefitHub.show(on: self)
  }
}
