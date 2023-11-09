import UIKit
import BuzzvilSDK

final class CustomFeedCpsAdView: BZVFeedAdView {
  let nativeAdView = BZVNativeAdView(frame: .zero)
  let mediaView = BZVMediaView(frame: .zero)
  let iconImageView = UIImageView(frame: .zero)
  let titleLabel = UILabel(frame: .zero)
  let descriptionLabel = UILabel(frame: .zero)
  let ctaView = BZVDefaultCtaView(frame: .zero)
  let priceLabel = UILabel(frame: .zero)
  let originalPriceLabel = UILabel(frame: .zero)
  let discountRateLabel = UILabel(frame: .zero)

  lazy var viewBinder = BZVNativeAdViewBinder { builder in
    builder.nativeAdView = self.nativeAdView
    builder.mediaView = self.mediaView
    builder.iconImageView = self.iconImageView
    builder.titleLabel = self.titleLabel
    builder.descriptionLabel = self.descriptionLabel
    builder.ctaView = self.ctaView
    // 부가 기능: 텍스트를 클릭할 수 있도록 설정합니다.
    builder.clickableViews = [
      self.mediaView,
      self.ctaView,
      self.priceLabel,
      self.originalPriceLabel,
      self.discountRateLabel,
    ]
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

  private func setupView() {
    // 광고 레이아웃 컴포넌트를 생성합니다.
    addSubview(nativeAdView)
    nativeAdView.addSubview(mediaView)
    nativeAdView.addSubview(iconImageView)
    nativeAdView.addSubview(titleLabel)
    nativeAdView.addSubview(descriptionLabel)
    nativeAdView.addSubview(ctaView)
    nativeAdView.addSubview(priceLabel)
    nativeAdView.addSubview(originalPriceLabel)
    nativeAdView.addSubview(discountRateLabel)
  }
  
  func setupLayout() {
    // LayoutConstraint 설정
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
    
    discountRateLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      discountRateLabel.leadingAnchor.constraint(equalTo: nativeAdView.leadingAnchor, constant: 8),
      discountRateLabel.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 8),
    ])
    
    priceLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      priceLabel.leadingAnchor.constraint(equalTo: discountRateLabel.trailingAnchor, constant: 8),
      priceLabel.topAnchor.constraint(equalTo: descriptionLabel.bottomAnchor, constant: 8),
    ])
    
    originalPriceLabel.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      originalPriceLabel.leadingAnchor.constraint(equalTo: nativeAdView.leadingAnchor, constant: 8),
      originalPriceLabel.topAnchor.constraint(equalTo: discountRateLabel.bottomAnchor, constant: 8),
      originalPriceLabel.bottomAnchor.constraint(equalTo: nativeAdView.bottomAnchor)
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

    let product = ad.product
    if product.discountedPrice != 0 {
      // 할인이 있는 쇼핑 광고
      priceLabel.text = NSNumber(value: product.discountedPrice).formattedString()
      originalPriceLabel.text = NSNumber(value: product.price).formattedString()
      let discountRate = (1 - product.discountedPrice / product.price) * 100
      discountRateLabel.text = String(format: "%d%%", Int(discountRate))
    } else {
      // 할인이 없는 쇼핑 광고
      priceLabel.text = NSNumber(value: product.discountedPrice).formattedString()
      originalPriceLabel.text = NSNumber(value: product.price).formattedString()
      discountRateLabel.text = "0%%"
    }
  }
}

extension NSNumber {
  func formattedString() -> String? {
    let numberFormatter = NumberFormatter()
    numberFormatter.numberStyle = .decimal
    return numberFormatter.string(from: self)
  }
}
