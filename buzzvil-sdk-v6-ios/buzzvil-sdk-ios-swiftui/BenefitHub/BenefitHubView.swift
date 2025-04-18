import SwiftUI
import BuzzvilSDK

struct BenefitHubView: View {
  @State private var benefitHubConfig: BuzzBenefitHubConfig?
  
  var body: some View {
    VStack(spacing: 16) {
      // 베네핏허브 표시하기
      Button("Show BenefitHub") {
        benefitHubConfig = BuzzBenefitHubConfig.Builder()
          .build()
      }
      
      // 럭키박스 표시하기
      Button("Show LuckyBox") {
        benefitHubConfig = BuzzBenefitHubConfig.Builder()
          .setRoutePath(BuzzBenefitHubRoutePath.luckyBox)
          .build()
      }
      
      // 미션팩 표시하기
      Button("Show MissionPack") {
        benefitHubConfig = BuzzBenefitHubConfig.Builder()
          .setRoutePath(BuzzBenefitHubRoutePath.missionPack)
          .build()
      }
      
      // 적립내역 표시하기
      Button("Show History") {
        benefitHubConfig = BuzzBenefitHubConfig.Builder()
          .setRoutePath(BuzzBenefitHubRoutePath.history)
          .build()
      }
      
      Spacer()
    }
    .frame(maxWidth: .infinity)
    .padding()
    .buttonStyle(CustomButtonStyle())
    .background(
      Group {
        if let config = benefitHubConfig {
          BenefitHubViewControllerAccessor(config: config)
            .frame(width: .zero, height: .zero)
            .onAppear {
              DispatchQueue.main.async {
                benefitHubConfig = nil
              }
            }
        }
      }
    )
  }
}

private struct BenefitHubViewControllerAccessor: UIViewControllerRepresentable {
  let config: BuzzBenefitHubConfig
  
  func makeUIViewController(context: Context) -> UIViewController {
    let viewController = UIViewController()
    
    DispatchQueue.main.async {
      guard let parent = viewController.parent else { return }
      
      let benefitHub = BuzzBenefitHub()
      benefitHub.setConfig(config)
      benefitHub.show(on: parent)
    }
    
    return viewController
  }
  
  func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
