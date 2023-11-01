import UIKit
import BuzzAdBenefitSDK

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
    // ...
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
