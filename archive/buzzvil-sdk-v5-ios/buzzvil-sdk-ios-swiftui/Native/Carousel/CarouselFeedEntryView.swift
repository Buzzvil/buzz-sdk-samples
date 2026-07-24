import UIKit
import BuzzvilSDK

final class CarouselFeedEntryView: BZVFeedEntryView {
  private lazy var button: UIButton = {
    let button = UIButton(type: .system)
    button.setTitle("포인트 더 받으러 가기", for: .normal)
    button.tintColor = .systemBlue
    return button
  }()
  
  override init(frame: CGRect) {
    super.init(frame: frame)
    setupView()
    setupLayout()
  }
  
  required init?(coder: NSCoder) {
    fatalError()
  }
  
  private func setupView() {
    addSubview(button)
    clickableViews = [button]
  }
  
  private func setupLayout() {
    button.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      button.topAnchor.constraint(equalTo: topAnchor),
      button.leadingAnchor.constraint(equalTo: leadingAnchor),
      button.trailingAnchor.constraint(equalTo: trailingAnchor),
      button.bottomAnchor.constraint(equalTo: bottomAnchor),
    ])
  }
}
