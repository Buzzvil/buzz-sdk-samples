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
  private lazy var bannerView: BuzzAdBenefitBannerView = {
    let bannerView = BuzzAdBenefitBannerView(frame: .zero)
    bannerView.delegate = self
    return bannerView
  }()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    let config = BuzzAdBenefitBannerConfig.Builder(placementID: "YOUR_PLACEMENT_ID")
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

extension BannerViewController: BuzzAdBenefitBannerViewDelegate {
  func bannerView(_ bannerView: BuzzAdBenefitBannerView, didLoadApid: String) {
    // Banner에 광고가 할당 되었을 때 호출 됩니다.
  }
  
  func bannerView(_ bannerView: BuzzAdBenefitBannerView, didFailApid: String, error: NSError) {
    // Banner에 광고 할당이 실패했을 때 호출 됩니다.
  }
  
  func bannerView(_ bannerView: BuzzAdBenefitBannerView, didClickApid: String) {
    // Banner가 클릭되었을 때 호출 됩니다.
  }
  
  func bannerView(_ bannerView: BuzzAdBenefitSDK.BuzzAdBenefitBannerView, didRemoveApid: String) {
    // Banner가 제거되었을 떄 호출 됩니다.
  }
}
