import UIKit
import BuzzvilSDK

final class CustomHeaderViewHolder: BZVFeedHeaderViewHolder {
  let headerLabel = UILabel(frame: .zero)

  override class func desiredHeight() -> CGFloat {
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
