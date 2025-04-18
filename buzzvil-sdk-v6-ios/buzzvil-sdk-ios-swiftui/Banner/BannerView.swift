import SwiftUI
import BuzzvilSDK

struct BannerView: View {
  var body: some View {
    VStack {
      BannerContainerView()
      
      Spacer()
    }
    .padding()
  }
}

struct BannerContainerView: UIViewControllerRepresentable {
  typealias UIViewControllerType = BannerViewController
  
  func makeUIViewController(context: Context) -> BannerViewController {
    return BannerViewController()
  }
  
  func updateUIViewController(_ uiViewController: BannerViewController, context: Context) {}
}

final class BannerViewController: UIViewController {
  private lazy var bannerView: BuzzBannerView = {
    let bannerView = BuzzBannerView(frame: .zero)
    bannerView.delegate = self
    return bannerView
  }()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    view.backgroundColor = .systemBackground
    
    let config = BuzzBannerConfig.Builder(placementId: "YOUR_PLACEMENT_ID")
      .setSize(.w320h50)
      .build()
    bannerView.setConfig(rootViewController: self, config: config)
    
    view.addSubview(bannerView)
    
    bannerView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      bannerView.centerXAnchor.constraint(equalTo: view.safeAreaLayoutGuide.centerXAnchor),
      bannerView.centerYAnchor.constraint(equalTo: view.safeAreaLayoutGuide.centerYAnchor),
      bannerView.widthAnchor.constraint(equalToConstant: 320),
      bannerView.heightAnchor.constraint(equalToConstant: 50),
    ])
  }
  
  override func viewWillAppear(_ animated: Bool) {
    super.viewWillAppear(animated)
    
    bannerView.requestAd()
  }
  
  override func viewWillDisappear(_ animated: Bool) {
    super.viewWillDisappear(animated)
    
    bannerView.removeAd()
  }
}

extension BannerViewController: BuzzBannerViewDelegate {
  func bannerView(_ bannerView: BuzzBannerView, didLoadApid: String) {
    // Banner에 광고가 할당 되었을 때 호출 됩니다.
  }
  
  func bannerView(_ bannerView: BuzzBannerView, didFailApid: String, error: Error) {
    // Banner에 광고 할당이 실패했을 때 호출 됩니다.
  }
  
  func bannerView(_ bannerView: BuzzBannerView, didClickApid: String) {
    // Banner가 클릭되었을 때 호출 됩니다.
  }
  
  func bannerView(_ bannerView: BuzzBannerView, didRemoveApid: String) {
    // Banner가 제거되었을 때 호출 됩니다.
  }
}
