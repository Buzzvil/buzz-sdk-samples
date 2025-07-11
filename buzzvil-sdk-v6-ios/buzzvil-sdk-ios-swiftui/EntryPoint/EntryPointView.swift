import SwiftUI
import BuzzvilSDK

struct EntryPointView: View {
  @State private var isLoading = false
  @State private var isFabPresented = false
  @State private var isBannerPresented = false
  @State private var isPopupPresented = false
  @State private var isBottomSheetPresented = false
  
  var body: some View {
    ZStack {
      VStack(spacing: 16) {
        Button("Show FAB") {
          isFabPresented = true
        }
        
        Button("Show Banner") {
          isBannerPresented = true
        }
        
        Button("Show Popup") {
          isPopupPresented = true
        }
        
        Button("Show BottomSheet") {
          isBottomSheetPresented = true
        }
        
        Button("Custom Entry") {
          guard let vc = UIApplication.shared.windows.first?.rootViewController else { return }
          BuzzEntryPoint.shared.customEntryPointClicked(identifier: "YOUR_CUSTOM_ENTRY_IDENTIFIER", on: vc)
        }
        
        BuzzEntryPointBannerView.Representable(isVisible: $isBannerPresented)
          .aspectRatio(351.0 / 112.0, contentMode: .fit)
          .transition(.opacity)
          .frame(maxWidth: .infinity)
        
        Spacer()
      }
      .frame(maxWidth: .infinity)
      .padding()
      .buttonStyle(CustomButtonStyle())
      
      if isLoading {
        Color.black.opacity(0.2)
          .edgesIgnoringSafeArea(.all)
        ProgressView()
          .progressViewStyle(CircularProgressViewStyle())
      }
    }
    .onAppear {
      loadEntryPoints()
    }
    .buzzEntryPointFab(isPresented: $isFabPresented)
    .buzzEntryPointPopup(isPresented: $isPopupPresented)
    .buzzEntryPointBottomSheet(isPresented: $isBottomSheetPresented)
  }
  
  private func loadEntryPoints() {
    isLoading = true
    BuzzEntryPoint.shared.load(
      onSuccess: { _ in
        isLoading = false
      },
      onFailure: { _ in
        isLoading = false
      }
    )
  }
}
