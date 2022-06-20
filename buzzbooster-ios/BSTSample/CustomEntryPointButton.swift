import UIKit
import BuzzBooster

class CustomEntryPointButton: BSTCampaignEntryView {
  let button = UIButton(frame: .zero)
  
  required init?(coder: NSCoder) {
    super.init(coder: coder)
    setupView()
    setupLayout()
  }
  
  override init(frame: CGRect) {
    super.init(frame: frame)
    setupView()
    setupLayout()
  }
  
  private func setupView() {
    button.setTitle("CustomEntryPoint", for: .normal)
    button.backgroundColor = .systemBlue
    addSubview(button)
    clickableViews = [button]
  }
  
  private func setupLayout() {
    button.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      button.trailingAnchor.constraint(equalTo: self.trailingAnchor),
      button.leadingAnchor.constraint(equalTo: self.leadingAnchor),
      button.heightAnchor.constraint(equalToConstant: 50),
    ])
  }
}
