import UIKit
import BuzzBoosterSDK
import BuzzAdBenefit

class ViewController: UIViewController {
  var scrollView: UIScrollView!
  var loginButton: UIButton!
  var showInAppMessageButton: UIButton!
  var showHomeButton: UIButton!
  var showAttendanceCampaignButton: UIButton!
  var likeButton: UIButton!
  var commentButton: UIButton!
  var postButton: UIButton!
  var stampActionStackView: UIStackView!
  var stackView: UIStackView!
  var login: Bool = false
  
  let buzzAdFeed = BZVBuzzAdFeed { builder in
    builder.config = BZVFeedConfig(block: { builder in
      builder.unitId = "11111111121111111"
      builder.title = "feed"
//      builder.shouldShowAppTrackingTransparencyDialog = true
//      builder.headerViewHolderClass = CustomHeaderViewHolder.self
//      builder.errorViewHolderClass = CustomFeedErrorViewHolder.self
//      builder.adViewHolderClass = BZVFeedAdViewHolder.self
//      builder.cpsAdViewHolderClass = BZVFeedAdViewHolder.self
    })
//    builder.theme = nil
  }
  let privacyPolicyManager = BuzzAdBenefit.privacyPolicyManager
  override func viewDidLoad() {
    super.viewDidLoad()
    setupView()
    setupLayout()
    bindEvent()
    
//    BZVFeedEntryView(frame: .zero).setEntryName("")
  }
  
  func setupView() {
    self.navigationItem.title = "BuzzBooster Sample"
    loginButton = UIButton(type: .system)
    loginButton.setTitle("Login", for: .normal)
    setButtonAttributes(button: loginButton)
    
    showInAppMessageButton = UIButton(type: .system)
    showInAppMessageButton.setTitle("showInAppMessage", for: .normal)
    setButtonAttributes(button: showInAppMessageButton)
    
    showHomeButton = UIButton(type: .system)
    showHomeButton.setTitle("showCampaign", for: .normal)
    setButtonAttributes(button: showHomeButton)
    
    showAttendanceCampaignButton = UIButton(type: .system)
    showAttendanceCampaignButton.setTitle("showAttendanceCampaign", for: .normal)
    setButtonAttributes(button: showAttendanceCampaignButton)
    
    likeButton = UIButton(type: .system)
    likeButton.setTitle("like", for: .normal)
    setButtonAttributes(button: likeButton)
    
    postButton = UIButton(type: .system)
    postButton.setTitle("post", for: .normal)
    setButtonAttributes(button: postButton)
    
    commentButton = UIButton(type: .system)
    commentButton.setTitle("comment", for: .normal)
    setButtonAttributes(button: commentButton)
    
    stampActionStackView = UIStackView(arrangedSubviews: [
      likeButton,
      postButton,
      commentButton,
    ])
    stampActionStackView.axis = .horizontal
    stampActionStackView.distribution = .fillEqually
    stampActionStackView.spacing = 5
    
    stackView = UIStackView(arrangedSubviews: [
      loginButton,
      showInAppMessageButton,
      showHomeButton,
      showAttendanceCampaignButton,
      stampActionStackView,
    ])
    stackView.axis = .vertical
    stackView.spacing = 8
    
    scrollView = UIScrollView(frame: .zero)
    scrollView.showsVerticalScrollIndicator = false
    scrollView.addSubview(stackView)
    view.addSubview(scrollView)
  }
  
  func setButtonAttributes(button: UIButton) {
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
  }
  
  func setupLayout() {
    scrollView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      scrollView.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor),
      scrollView.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor),
      scrollView.topAnchor.constraint(equalTo: view.layoutMarginsGuide.topAnchor),
      scrollView.bottomAnchor.constraint(equalTo: view.layoutMarginsGuide.bottomAnchor),
    ])

    stackView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      stackView.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor),
      stackView.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor),
      stackView.topAnchor.constraint(equalTo: scrollView.topAnchor),
      stackView.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor),
      stackView.widthAnchor.constraint(equalTo: scrollView.widthAnchor, constant:-4)
    ])
    
    for arrangedSubview in stackView.arrangedSubviews {
      setupSizeConstraintsOfArrangedSubview(view: arrangedSubview)
    }
  }
  
  func setupSizeConstraintsOfArrangedSubview(view: UIView) {
    view.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      view.widthAnchor.constraint(equalTo: stackView.widthAnchor),
      view.heightAnchor.constraint(equalToConstant: 48)
    ])
  }
  
  func bindEvent() {
    loginButton.addTarget(self, action: #selector(loginButtonAction), for: .touchUpInside)
    showInAppMessageButton.addTarget(self, action: #selector(showInAppMessageButtonAction), for: .touchUpInside)
    showAttendanceCampaignButton.addTarget(self, action: #selector(showAttendanceCampaignListButtonAction), for: .touchUpInside)
    showHomeButton.addTarget(self, action: #selector(showHomeButtonAction), for: .touchUpInside)
    likeButton.addTarget(self, action: #selector(likeButtonAction), for: .touchUpInside)
    postButton.addTarget(self, action: #selector(postButtonAction), for: .touchUpInside)
    commentButton.addTarget(self, action: #selector(commentButtonAction), for: .touchUpInside)
  }
  
  @objc func loginButtonAction(button: UIButton!) {
    if (self.login) {
      BuzzAdBenefit.logout()
      BuzzBooster.setUser(nil)
      loginButton.setTitle("Login", for: .normal)
    } else {
      let user = BSTUser { builder in
        builder.userId = AppDelegate.USER_ID
        builder.marketingStatus = .optIn
        builder.properties = ["login_type": "sns(Facebook)"]
      }
      BuzzBooster.setUser(user)
      BuzzBooster.showInAppMessage(with: self)
      
      BuzzAdBenefit.login { builder in
        builder.userId = AppDelegate.USER_ID
        builder.gender = .male // 남성 사용자
        builder.birthYear = 2024
      } onSuccess: {
        print("success login")
      } onFailure: { error in
        print("error login \(error)")
      }

      loginButton.setTitle("Logout", for: .normal)
    }
    self.login = !self.login
  }
  
  @objc func showInAppMessageButtonAction() {
    BuzzBooster.showInAppMessage(with: self)
  }
  
  @objc func showHomeButtonAction() {
//    BuzzBooster.showHome(with: self)
    let feedViewController = buzzAdFeed.viewController
    self.navigationController?.pushViewController(feedViewController, animated: true)
  }
  
  @objc func showAttendanceCampaignListButtonAction() {
//    BuzzBooster.showCampaign(by: .attendance, with: self)
    let feedViewController = buzzAdFeed.viewController
    self.present(feedViewController, animated: true, completion: nil)
  }
  
  @objc func likeButtonAction() {
    BuzzBooster.sendEvent(
      "bb_like",
      values: [
        "like_comment_id": "post_1"
      ]
    )
  }
  
  @objc func commentButtonAction() {
    BuzzBooster.sendEvent(
      "bb_comment",
      values: [
        "commented_content_id": "1",
        "comment": "Great Post!",
      ]
    )
  }
  
  @objc func postButtonAction() {
    BuzzBooster.sendEvent(
      "bb_posting_content",
      values: [
        "posted_content_id": "1",
      ])
  }
}

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
  }
  
  override func availableRewardDidUpdate(_ reward: Int) {
    headerLabel.text = "리워드 \(reward)원"
  }
}

final class CustomFeedErrorViewHolder: BZVFeedErrorViewHolder {
  let label = UILabel()

  required init?(coder: NSCoder) {
    super.init(coder: coder)
    setupView()
  }

  override init(frame: CGRect) {
    super.init(frame: frame)
    setupView()
  }

  private func setupView() {
    label.text = "error view"
    addSubview(label)
  }

  override func updateViewWithError(_ error: Error) {
    // error에 따라 UI를 업데이트 할 수 있는 코드 작성
  }
}
