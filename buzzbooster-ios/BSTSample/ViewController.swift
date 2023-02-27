import UIKit
import BuzzBooster
import Toast

class ViewController: UIViewController {
  var scrollView: UIScrollView!
  var loginButton: UIButton!
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
    
    stackView = UIStackView(arrangedSubviews: [
      loginButton,
      customEntryPointButton
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
  }
  
  @objc func loginButtonAction(button: UIButton!) {
    if (self.login) {
      BuzzBooster.setUser(nil)
      loginButton.setTitle("Login", for: .normal)
    } else {
      let user = BSTUser { builder in
        builder.userId = AppDelegate.USER_ID as NSString
        builder.marketingStatus = .optIn
        builder.properties = ["login_type": "sns(Facebook)"]
      }
      BuzzBooster.setUser(user)
      BuzzBooster.showInAppMessage(with: self)
      loginButton.setTitle("Logout", for: .normal)
    }
    self.login = !self.login
  }
}

