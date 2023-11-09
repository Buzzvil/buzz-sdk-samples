import UIKit
import BuzzvilSDK

final class CustomFeedHeaderView: BZVFeedHeaderView {
  let headerLabel = UILabel(frame: .zero)
  
  override class func desiredHeight(_ width: CGFloat) -> CGFloat {
    return 100.0
  }

  required init?(coder: NSCoder) {
    super.init(coder: coder)
    setUpView()
  }

  override init(frame: CGRect) {
    super.init(frame: frame)
    setUpView()
  }

  func setUpView() {
    addSubview(headerLabel)
    headerLabel.textColor = .white
    headerLabel.text = "HEADER CUSTOM"

    // LayoutConstraint 설정
    // ...
    headerLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      headerLabel.trailingAnchor.constraint(equalTo: trailingAnchor),
      headerLabel.centerYAnchor.constraint(equalTo: centerYAnchor),
    ])
  }
  
  override func availableRewardDidUpdate(_ reward: Int) {
    headerLabel.text = "리워드 \(reward)원"
  }
}
