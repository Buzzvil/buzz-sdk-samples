import UIKit
import BuzzAdBenefitSDK

final class CustomCtaView: UIStackView, BZVCtaViewProtocol {
  let rewardImageView = UIImageView(frame: .zero)
  let rewardLabel = UILabel(frame: .zero)
  let ctaLabel = UILabel(frame: .zero)

  required init(coder: NSCoder) {
    super.init(coder: coder)
    setUpView()
  }

  override init(frame: CGRect) {
    super.init(frame: frame)
    setUpView()
  }

  private func setUpView() {
    addArrangedSubview(rewardImageView)
    addArrangedSubview(rewardLabel)
    addArrangedSubview(ctaLabel)
  }

  // MARK: BZVCtaViewProtocol
  func renderRewardNotAvailableViewState(withCtaText ctaText: String) {
    // 리워드가 없는 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
    rewardImageView.isHidden = true
    rewardLabel.isHidden = true
    ctaLabel.text = ctaText
  }

  func renderRewardAvailableViewState(withCtaText ctaText: String, reward: Int) {
    // 리워드가 있는 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
    rewardImageView.isHidden = false
    rewardLabel.isHidden = false

    rewardImageView.image = UIImage(named: "YOUR_REWARD_IMAGE")
    rewardLabel.text = "+\(reward)"
    ctaLabel.text = ctaText
  }

  func renderParticipatingViewState(withCtaText ctaText: String) {
    // 참여 확인 중인 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
    rewardImageView.isHidden = true
    rewardLabel.isHidden = true
    ctaLabel.text = "YOUR_PARTICIPATING_TEXT"
  }

  func renderParticipatedViewState(withCtaText ctaText: String) {
    // 참여 완료한 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
    rewardImageView.isHidden = false
    rewardLabel.isHidden = true

    rewardImageView.image = UIImage(named: "YOUR_PARTICIPATED_IMAGE")
    ctaLabel.text = "YOUR_PARTICIPATED_TEXT"
  }
}
