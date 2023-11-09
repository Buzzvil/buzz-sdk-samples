import UIKit
import BuzzvilSDK

final class CustomFeedAdView: BZVFeedAdView {
  let nativeAdView = BZVNativeAdView(frame: .zero)
  let mediaView = BZVMediaView(frame: .zero)
  let iconImageView = UIImageView(frame: .zero)
  let titleLabel = UILabel(frame: .zero)
  let descriptionLabel = UILabel(frame: .zero)
  let ctaView = BZVDefaultCtaView(frame: .zero)

  lazy var viewBinder = BZVNativeAdViewBinder { builder in
    builder.nativeAdView = self.nativeAdView
    builder.mediaView = self.mediaView
    builder.iconImageView = self.iconImageView
    builder.titleLabel = self.titleLabel
    builder.descriptionLabel = self.descriptionLabel
    builder.ctaView = self.ctaView
  }

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
  
  override class func desiredHeight(_ width: CGFloat) -> CGFloat {
    return width * 0.8
  }

  func setupView() {
    addSubview(nativeAdView)
    nativeAdView.addSubview(mediaView)
    nativeAdView.addSubview(iconImageView)
    nativeAdView.addSubview(titleLabel)
    nativeAdView.addSubview(descriptionLabel)
    nativeAdView.addSubview(ctaView)
    titleLabel.textColor = .red
  }
  
  func setupLayout() {
    // LayoutConstraint 설정
    // ...
    nativeAdView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      nativeAdView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16),
      nativeAdView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16),
      nativeAdView.topAnchor.constraint(equalTo: topAnchor, constant: 8)
    ])
    
    mediaView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      mediaView.topAnchor.constraint(equalTo: nativeAdView.topAnchor),
      mediaView.leadingAnchor.constraint(equalTo: nativeAdView.leadingAnchor),
      mediaView.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor),
      mediaView.heightAnchor.constraint(equalTo: mediaView.widthAnchor, multiplier: 627/1200),
    ])
    
    iconImageView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      iconImageView.topAnchor.constraint(equalTo: mediaView.bottomAnchor, constant: 8),
      iconImageView.leadingAnchor.constraint(equalTo: mediaView.leadingAnchor, constant: 8),
      iconImageView.heightAnchor.constraint(equalToConstant: 32),
      iconImageView.widthAnchor.constraint(equalToConstant: 32)
    ])
    
    titleLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      titleLabel.centerYAnchor.constraint(equalTo: iconImageView.centerYAnchor),
      titleLabel.leadingAnchor.constraint(equalTo: iconImageView.trailingAnchor, constant: 8),
      titleLabel.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor, constant: -8)
    ])
    
    descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      descriptionLabel.topAnchor.constraint(equalTo: iconImageView.bottomAnchor, constant: 8),
      descriptionLabel.leadingAnchor.constraint(equalTo: iconImageView.leadingAnchor),
      descriptionLabel.trailingAnchor.constraint(equalTo: titleLabel.trailingAnchor)
    ])
    
    ctaView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      ctaView.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 8),
      ctaView.trailingAnchor.constraint(equalTo: nativeAdView.trailingAnchor, constant: -8),
      ctaView.bottomAnchor.constraint(equalTo: nativeAdView.bottomAnchor, constant: -8),
      ctaView.heightAnchor.constraint(equalToConstant: 32)
    ])
  }
  
  override func renderAd(_ ad: BZVNativeAd) {
    viewBinder.bind(with: ad)
    ad.delegate = self
  }
}

extension CustomFeedAdView: BZVNativeAdEventDelegate {
  func didImpress(_ nativeAd: BZVNativeAd) {
  }
  
  func didClick(_ nativeAd: BZVNativeAd) {
  }
  
  func didRequestReward(for nativeAd: BZVNativeAd) {
  }
  
  func didReward(for nativeAd: BZVNativeAd, with result: BZVRewardResult) {
  }
  
  func didParticipateAd(_ nativeAd: BZVNativeAd) {
  }
}
