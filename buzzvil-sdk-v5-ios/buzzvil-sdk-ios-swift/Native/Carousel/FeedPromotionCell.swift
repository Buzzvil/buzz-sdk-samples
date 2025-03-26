import UIKit
import BuzzvilSDK

class FeedPromotionCell: UICollectionViewCell {
  private lazy var feedPromotionView: BZVFeedPromotionView = BZVFeedPromotionView(frame: .zero)
  private lazy var creativeView: UIImageView = UIImageView(frame: .zero)
  private lazy var iconImageView: UIImageView = UIImageView(frame: .zero)
  private lazy var titleLabel: UILabel = UILabel(frame: .zero)
  private lazy var ctaView: BZVDefaultCtaView = BZVDefaultCtaView(frame: .zero)
  
  // FeedPromotionView와 하위 컴포넌트를 연결합니다.
   private lazy var viewBinder = BZVFeedPromotionViewBinder
     .Builder(unitId: "YOUR_NATIVE_UNIT_ID")
     .feedPromotionView(feedPromotionView)
     .creativeView(creativeView)
     .iconImageView(iconImageView)
     .titleLabel(titleLabel)
     .ctaView(ctaView)
     .build()
  
  override init(frame: CGRect) {
    super.init(frame: frame)
    setupView()
    setupLayout()
  }
  
  required init?(coder: NSCoder) {
    fatalError()
  }
  
  private func setupView() {
    contentView.addSubview(feedPromotionView)
    feedPromotionView.addSubview(creativeView)
    feedPromotionView.addSubview(iconImageView)
    feedPromotionView.addSubview(titleLabel)
    feedPromotionView.addSubview(ctaView)
  }
  
  private func setupLayout() {
    // AutoLayout Constraints를 설정하세요.
    feedPromotionView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      feedPromotionView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: 16),
      feedPromotionView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -16),
      feedPromotionView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor, constant: 8)
    ])
    
    creativeView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      creativeView.topAnchor.constraint(equalTo: feedPromotionView.topAnchor),
      creativeView.leadingAnchor.constraint(equalTo: feedPromotionView.leadingAnchor),
      creativeView.trailingAnchor.constraint(equalTo: feedPromotionView.trailingAnchor),
      creativeView.heightAnchor.constraint(equalTo: feedPromotionView.widthAnchor, multiplier: 627/1200),
    ])
    
    iconImageView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      iconImageView.topAnchor.constraint(equalTo: creativeView.bottomAnchor, constant: 8),
      iconImageView.leadingAnchor.constraint(equalTo: creativeView.leadingAnchor, constant: 8),
      iconImageView.heightAnchor.constraint(equalToConstant: 32),
      iconImageView.widthAnchor.constraint(equalToConstant: 32)
    ])
    
    titleLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      titleLabel.centerYAnchor.constraint(equalTo: iconImageView.centerYAnchor),
      titleLabel.leadingAnchor.constraint(equalTo: iconImageView.trailingAnchor, constant: 8),
      titleLabel.trailingAnchor.constraint(equalTo: feedPromotionView.trailingAnchor, constant: -8)
    ])
    
    ctaView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      ctaView.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 8),
      ctaView.trailingAnchor.constraint(equalTo: feedPromotionView.trailingAnchor, constant: -8),
      ctaView.bottomAnchor.constraint(equalTo: feedPromotionView.bottomAnchor, constant: -8),
      ctaView.heightAnchor.constraint(equalToConstant: 32)
    ])
  }
  
  override func prepareForReuse() {
      super.prepareForReuse()
      // prepareForReuse 내에서 unbind를 반드시 호출하여 cell을 재사용할 때 문제가 발생하지 않게 합니다.
      viewBinder.unbind()
    }

    // collectionView의 collectionView(_:cellForItemAt:) 시점에 호출합니다.
    func bind() {
      // BZVFeedPromotionViewBinder의 bind()를 호출하면 베네핏허브 진입 슬라이드 데이터가 자동으로 제공됩니다.
      viewBinder.bind()
    }
}
