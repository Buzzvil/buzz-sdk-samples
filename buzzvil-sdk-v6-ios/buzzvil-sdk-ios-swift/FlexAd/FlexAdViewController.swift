import UIKit
import BuzzvilSDK

class FlexAdViewController: UIViewController {

    private let buzzFlex = BuzzFlex(unitId: "YOUR_FLEX_AD_UNIT_ID")

    private lazy var flexAdView: BuzzFlexAdView = {
        let view = BuzzFlexAdView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        navigationItem.title = "FlexAd"

        setupLayout()
        loadAd()
    }

    private func setupLayout() {
        view.addSubview(flexAdView)
        NSLayoutConstraint.activate([
            flexAdView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 16),
            flexAdView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            flexAdView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
        ])
    }

    private func loadAd() {
        buzzFlex.delegate = self
        buzzFlex.load()
    }
}

extension FlexAdViewController: BuzzFlexDelegate {
    func buzzFlexOnSuccess() {
        // 광고 로드 성공 시 호출됩니다.
        // BuzzFlexAdView에 광고를 표시합니다.
        flexAdView.bind(buzzFlex)
    }

    func buzzFlexOnFailure(_ error: Error) {
        // 광고 로드 실패 시 호출됩니다.
    }

    func buzzFlexOnClicked() {
        // 광고 클릭 시 호출됩니다.
    }
}
