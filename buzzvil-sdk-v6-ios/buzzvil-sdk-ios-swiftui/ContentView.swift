import SwiftUI
import BuzzvilSDK

struct ContentView: View {
  var body: some View {
    NavigationView {
      VStack(spacing: 16) {
        NavigationLink("Native") {
          NativeView()
            .navigationTitle("Native")
        }
        
        NavigationLink("Interstitial") {
          InterstitialView()
            .navigationTitle("Interstitial")
        }
        
        NavigationLink("Carousel") {
          CarouselView()
            .navigationTitle("Carousel")
        }
        
        NavigationLink("BenefitHub") {
          BenefitHubView()
            .navigationTitle("BenefitHub")
        }
        
        NavigationLink("BenefitHub Container") {
          BenefitHubContainerView()
            .navigationTitle("BenefitHub Container")
        }
        
        NavigationLink("Banner") {
          BannerView()
            .navigationTitle("Banner")
        }
        
        Button("Inquiry") {
          BuzzAdBenefit.shared.openInquiryPage(unitId: "YOUR_UNIT_ID")
        }
        
        PrivacyConsentView()
        
        Spacer()
      }
      .frame(maxWidth: .infinity)
      .padding()
      .navigationTitle("BuzzvilSDK-SwiftUI")
      .buttonStyle(CustomButtonStyle())
    }
  }
}

struct CustomButtonStyle: ButtonStyle {
  func makeBody(configuration: Configuration) -> some View {
    configuration.label
      .frame(height: 48)
      .frame(maxWidth: .infinity)
      .background(Color(white: 0.15))
      .foregroundColor(.white)
      .cornerRadius(8)
      .font(.system(size: 16))
      .scaleEffect(configuration.isPressed ? 0.98 : 1.0)
  }
}

#Preview {
  ContentView()
}
