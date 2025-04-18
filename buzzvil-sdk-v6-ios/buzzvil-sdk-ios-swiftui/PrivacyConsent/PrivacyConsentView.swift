import SwiftUI
import BuzzvilSDK

struct PrivacyConsentView: View {
  @State private var statusText: String = "Not loaded"
  
  var body: some View {
    VStack(alignment: .leading, spacing: 12) {
      Text("PrivacyConsent: \(statusText)")
      
      HStack(spacing: 12) {
        Button("Load status") {
          BuzzAdBenefit.shared.loadPrivacyConsentStatus(
            onSuccess: { status in
              switch status {
              case .granted:
                statusText = "Granted"
              case .revoked:
                statusText = "Revoked"
              }
            },
            onFailure: { error in
              statusText = "load failure \(error.localizedDescription)"
            }
          )
        }
        
        Button("Grant consent") {
          BuzzAdBenefit.shared.grantPrivacyConsent(
            onSuccess: {
              statusText = "Granted"
            },
            onFailure: { error in
              statusText = "grant failure \(error.localizedDescription)"
            }
          )
        }
      }
    }
    .padding()
    .background(Color(.secondarySystemGroupedBackground))
    .cornerRadius(8)
    .buttonStyle(CustomButtonStyle())
  }
}
