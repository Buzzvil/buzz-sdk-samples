import UIKit
import BuzzBoosterSDK
import BuzzvilSDK

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
      builder.unitID = "11111111121111111"
      // builder.title = "feed" https://docs.buzzvil.com/docs/buzzbenefit-ios/v5/migration/#%EA%B8%B0%EB%B3%B8-%EC%84%A4%EC%A0%95-%EC%9D%B4%EC%99%B8%EC%9D%98-%EB%B2%A0%EB%84%A4%ED%95%8F-%ED%97%88%EB%B8%8C-%ED%91%9C%EC%8B%9C%ED%95%98%EA%B8%B0
    })
  }
  let privacyPolicyManager = BuzzAdBenefit.privacyPolicyManager
  override func viewDidLoad() {
    super.viewDidLoad()
    setupView()
    setupLayout()
    bindEvent()
    // https://docs.buzzvil.com/docs/buzzbenefit-ios/v5/migration/#v345-%EC%95%B1-ui%EC%97%90-%EC%A7%84%EC%9E%85-%EA%B2%BD%EB%A1%9C-%EC%B6%94%EA%B0%80--v51-%EC%95%B1-ui%EC%97%90-%EC%A7%84%EC%9E%85-%EA%B2%BD%EB%A1%9C-%EA%B5%AC%ED%98%84
    // BZVFeedEntryView(frame: .zero).setEntryName(entryName)
    // setEntryName이 여전히 남아있음
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
      BuzzBenefit.shared.logout()
      BuzzBooster.setUser(nil)
      loginButton.setTitle("Login", for: .normal)
    } else {
      // builder.marketingStatus = .optIn
      // builder.properties = ["login_type": "sns(Facebook)"]
      // BuzzBooster에서 필요한 user 세팅이 지원이 안됨
      let user = BSTUser { builder in
        builder.userId = AppDelegate.USER_ID
        builder.marketingStatus = .optIn
        builder.properties = ["login_type": "sns(Facebook)"]
      }
      BuzzBooster.setUser(user)
      BuzzBooster.showInAppMessage(with: self)
      
      let buzzBenefitUser = BuzzBenefitUser.Builder(userID: "USER_ID")
        .setGender(.male)
        .setBirthYear(2024)
        .build()

      BuzzBenefit.shared.login(
        with: buzzBenefitUser,
        onSuccess: {
          print("success login")
        },
        onFailure: { error in
          print("error login \(error)")
        }
      )
      print(BuzzBenefit.shared.isLoggedIn())
      loginButton.setTitle("Logout", for: .normal)
    }
    self.login = !self.login
  }
  
  @objc func showInAppMessageButtonAction() {
    BuzzBooster.showInAppMessage(with: self)
  }
  
  @objc func showHomeButtonAction() {
    // 변경 전 코드가 아님 : https://docs.buzzvil.com/docs/buzzbenefit-ios/v5/migration/#%EB%B2%A0%EB%84%A4%ED%95%8F%ED%97%88%EB%B8%8C-%ED%91%9C%EC%8B%9C%ED%95%98%EA%B8%B0
    // 둘 다 지원
    buzzAdFeed.show(on: self)
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

// https://docs.buzzvil.com/docs/buzzbenefit-ios/v5/migration/#%ED%97%A4%EB%8D%94-%EC%98%81%EC%97%AD-%EC%9E%90%EC%B2%B4-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
// 기존과 동일하지 않음 BZVFeedHeaderViewHolder -> BZVFeedHeaderView
// + override class func desiredHeight() -> CGFloat --> override class func desiredHeight(_ width: CGFloat) -> CGFloat
final class CustomHeaderViewHolder: BZVFeedHeaderView {
  let headerLabel = UILabel(frame: .zero)

  override class func desiredHeight(_ width: CGFloat) -> CGFloat {
    return 60
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
