import UIKit
import BuzzvilSDK

class FeedViewController: UIViewController {
  private lazy var scrollView: UIScrollView = {
    let view = UIScrollView()
    view.showsVerticalScrollIndicator = false
    return view
  }()
  
  private lazy var rootStackView: UIStackView = {
    let stackView = UIStackView(frame: .zero)
    stackView.axis = .vertical
    stackView.spacing = 8
    stackView.alignment = .fill
    stackView.distribution = .fill
    return stackView
  }()
  
  private lazy var showBenefitHubButton: UIButton = {
    let button = UIButton(type: .system)
    button.setTitle("Show BenefitHub", for: .normal)
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
    button.addTarget(self, action: #selector(showBenefitHub), for: .touchUpInside)
    return button
  }()
  
  private lazy var filterTextField: LabeledTextField = {
    let view = LabeledTextField()
    view.titleLabel.text = "Filter"
    view.textField.placeholder = "Enter Filter Name"
    view.textField.text = "콘텐츠적립"
    return view
  }()
  
  private lazy var showBenefitHubWithContentsFilterButton: UIButton = {
    let button = UIButton(type: .system)
    button.setTitle("Show BenefitHub with filter", for: .normal)
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
    button.addTarget(self, action: #selector(showBenefitHubWithContentsFilter), for: .touchUpInside)
    return button
  }()
  
  private lazy var showLuckyBoxButton: UIButton = {
    let button = UIButton(type: .system)
    button.setTitle("Show LuckyBox", for: .normal)
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
    button.addTarget(self, action: #selector(showLuckyBox), for: .touchUpInside)
    return button
  }()
  
  // 멤버 변수로 선언해야 정상동작합니다.
  private lazy var feed = BZVBuzzAdFeed { _ in }
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    setupView()
    setupLayout()
  }
  
  private func setupView() {
    navigationItem.title = "BenefitHub"
    
    view.addSubview(scrollView)
    scrollView.addSubview(rootStackView)
    rootStackView.addArrangedSubview(showBenefitHubButton)
    rootStackView.addArrangedSubview(filterTextField)
    rootStackView.addArrangedSubview(showBenefitHubWithContentsFilterButton)
    rootStackView.addArrangedSubview(showLuckyBoxButton)
  }
  
  private func setupLayout() {
    scrollView.translatesAutoresizingMaskIntoConstraints = false
    rootStackView.translatesAutoresizingMaskIntoConstraints = false
    
    NSLayoutConstraint.activate([
      scrollView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 16),
      scrollView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -16),
      scrollView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 8),
      scrollView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
      
      rootStackView.topAnchor.constraint(equalTo: scrollView.topAnchor),
      rootStackView.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor),
      rootStackView.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor),
      rootStackView.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor),
      rootStackView.widthAnchor.constraint(equalTo: scrollView.widthAnchor),
      
      showBenefitHubButton.heightAnchor.constraint(equalToConstant: 48),
      filterTextField.heightAnchor.constraint(equalToConstant: 48),
      showBenefitHubWithContentsFilterButton.heightAnchor.constraint(equalToConstant: 48),
      showLuckyBoxButton.heightAnchor.constraint(equalToConstant: 48),
    ])
  }
  
  // 피드 표시하기
  @objc
  private func showBenefitHub() {
    feed = BZVBuzzAdFeed { _ in }
    
    feed.show(on: self)
  }
  
  // 피드 표시하기
  func showFeedWithUnitID() {
    let buzzAdFeed = BZVBuzzAdFeed { builder in
      builder.config = BZVFeedConfig { builder in
        builder.unitID = "SECOND_FEED_UNIT_ID"
      }
    }
    buzzAdFeed.show(on: self)
  }
  
  // 적립 가능한 포인트 표시하기
  func getAvailiableReward() {
    let buzzAdFeed = BZVBuzzAdFeed { _ in }
    buzzAdFeed.load(
      onSuccess: {
        // 광고 로드에 성공했을 경우
        // 적립 가능한 포인트를 직접 구현한 UI에 업데이트합니다.
        let availiableReward = buzzAdFeed.availableRewards
      },
      onFailure: { error in
        // 적립 가능한 포인트를 가져올 수 없는 경우
      }
    )
  }
  
  // LuckyBox 를 바로 열기
  @objc
  private func showLuckyBox() {
    let feedConfig = BZVFeedConfig { builder in
      builder.unitID = "YOUR_FEED_UNIT_ID"
      builder.initialNavigationPage = .luckyBox
    }
    
    feed = BZVBuzzAdFeed(block: { builder in
      builder.config = feedConfig
    })
    feed.show(on: self)
  }
  
  // 베네핏 허브 열리면서 필터 선택 하기
  @objc
  private func showBenefitHubWithContentsFilter() {
    let feedConfig = BZVFeedConfig { builder in
      builder.unitID = "YOUR_FEED_UNIT_ID"
      builder.initialSelectedFilterName = filterTextField.textField.text
    }
    
    feed = BZVBuzzAdFeed(block: { builder in
      builder.config = feedConfig
    })
    feed.show(on: self)
  }
}
