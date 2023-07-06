import UIKit
import BuzzBoosterSDK
import Toast

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
  var campaignFloatingActionButton: BSTCampaignFloatingActionButton!
  private lazy var customEntryPointButton: CustomEntryPointButton = {
    let view = CustomEntryPointButton(frame: .zero)
    view.setEntryName("your_custom_entry_point_1")
    return view
  }()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    setupView()
    setupLayout()
    bindEvent()
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
    
    campaignFloatingActionButton = BSTCampaignFloatingActionButton()
    
    scrollView = UIScrollView(frame: .zero)
    scrollView.showsVerticalScrollIndicator = false
    scrollView.addSubview(stackView)
    view.addSubview(scrollView)
    view.addSubview(campaignFloatingActionButton)
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
    
    campaignFloatingActionButton.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      campaignFloatingActionButton.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -30),
      campaignFloatingActionButton.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: -30),
      campaignFloatingActionButton.heightAnchor.constraint(equalToConstant: 50),
      campaignFloatingActionButton.widthAnchor.constraint(equalToConstant: 50)
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
    showHomeButton.addTarget(self, action: #selector(showHomeButtonAction), for: .touchUpInside)
    likeButton.addTarget(self, action: #selector(likeButtonAction), for: .touchUpInside)
    postButton.addTarget(self, action: #selector(postButtonAction), for: .touchUpInside)
    commentButton.addTarget(self, action: #selector(commentButtonAction), for: .touchUpInside)
  }
  
  @objc func loginButtonAction(button: UIButton!) {
    if (self.login) {
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
      loginButton.setTitle("Logout", for: .normal)
    }
    self.login = !self.login
  }
  
  @objc func showInAppMessageButtonAction() {
    BuzzBooster.showInAppMessage(with: self)
  }
  
  @objc func showHomeButtonAction() {
    BuzzBooster.showHome(with: self)
  }
  
  @objc func showAttendanceCampaignListButtonAction() {
    BuzzBooster.showCampaign(by: .attendance, with: self)
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
