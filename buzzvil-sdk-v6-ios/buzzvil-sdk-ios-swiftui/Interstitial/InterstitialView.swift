import SwiftUI
import BuzzvilSDK

struct InterstitialView: View {
  @StateObject private var controller = InterstitialController()
  @State private var showAd = false
  
  var body: some View {
    Color.clear
      .background(
        showAd ? InterstitialViewControllerAccessor(
          onPresent: { viewController in
            controller.interstitial.present(on: viewController)
          }
        ).frame(width: .zero, height: .zero) : nil
      )
      .onAppear {
        controller.loadAd()
      }
      .onReceive(controller.$isLoaded) { isLoaded in
        guard isLoaded else { return }
        showAd = true
      }
  }
}

private struct InterstitialViewControllerAccessor: UIViewControllerRepresentable {
  let onPresent: (UIViewController) -> Void
  
  func makeUIViewController(context: Context) -> UIViewController {
    let viewController = UIViewController()
    DispatchQueue.main.async {
      guard let parent = viewController.parent else { return }
      onPresent(parent)
    }
    return viewController
  }
  
  func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
