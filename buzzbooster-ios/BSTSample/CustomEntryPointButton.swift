import UIKit
import BuzzBooster

final class CustomEntryPointButton: BSTCampaignEntryView {
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
    button.setTitle("Custom Entry View", for: .normal)
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    self.addSubview(button)
    clickableViews = [button]
  }
  
  private func setupLayout() {
    button.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      button.leadingAnchor.constraint(equalTo: self.leadingAnchor),
      button.trailingAnchor.constraint(equalTo: self.trailingAnchor),
      button.topAnchor.constraint(equalTo: self.topAnchor),
      button.bottomAnchor.constraint(equalTo: self.bottomAnchor),
    ])
  }
}
