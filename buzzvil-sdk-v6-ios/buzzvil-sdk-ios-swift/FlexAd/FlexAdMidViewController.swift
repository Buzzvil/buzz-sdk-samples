import UIKit
import BuzzvilSDK

/// FlexAd를 스크롤 피드 중간에 배치해, 위/아래 콘텐츠 사이에서의 뷰어빌리티 동작을 확인하는 예시 화면.
class FlexAdMidViewController: UIViewController {

    private let buzzFlex = BuzzFlex(unitId: "YOUR_FLEX_AD_UNIT_ID")

    private lazy var flexAdView: BuzzFlexAdView = {
        let view = BuzzFlexAdView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()

    private let scrollView = UIScrollView()

    private let contentStackView: UIStackView = {
        let stack = UIStackView()
        stack.axis = .vertical
        stack.spacing = 16
        stack.translatesAutoresizingMaskIntoConstraints = false
        return stack
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        navigationItem.title = "FlexAd (Mid)"

        setupLayout()
        setupSampleContent()
        loadAd()
    }

    private func setupLayout() {
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(scrollView)
        scrollView.addSubview(contentStackView)

        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: view.bottomAnchor),

            contentStackView.topAnchor.constraint(equalTo: scrollView.contentLayoutGuide.topAnchor, constant: 16),
            contentStackView.leadingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.leadingAnchor),
            contentStackView.trailingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.trailingAnchor),
            contentStackView.bottomAnchor.constraint(equalTo: scrollView.contentLayoutGuide.bottomAnchor, constant: -24),
            // 세로 스크롤만 되도록 stackView 너비를 scrollView 프레임 너비에 고정.
            contentStackView.widthAnchor.constraint(equalTo: scrollView.frameLayoutGuide.widthAnchor),
        ])
    }

    private func setupSampleContent() {
        // 위쪽 리스트
        for (index, item) in Self.sampleFeed.enumerated() {
            let tint = Self.thumbnailTints[index % Self.thumbnailTints.count]
            contentStackView.addArrangedSubview(makeSampleCard(item, tint: tint))
        }
        // 가운데 FlexAd
        contentStackView.addArrangedSubview(flexAdView)
        // 아래쪽 리스트
        for (index, item) in Self.sampleFeed.enumerated() {
            let tint = Self.thumbnailTints[index % Self.thumbnailTints.count]
            contentStackView.addArrangedSubview(makeSampleCard(item, tint: tint))
        }
    }

    private func loadAd() {
        buzzFlex.delegate = self
        buzzFlex.load()
    }

    // MARK: - 예시용 샘플 콘텐츠

    private struct SampleItem {
        let title: String
        let body: String
        let symbol: String
    }

    private static let thumbnailTints: [UIColor] = [
        .systemBlue, .systemGreen, .systemOrange, .systemPurple, .systemPink, .systemTeal,
    ]

    private static let sampleFeed: [SampleItem] = [
        SampleItem(title: "샘플 콘텐츠 · 오늘의 주요 소식", body: "스크롤 확인용 예시 콘텐츠입니다.", symbol: "doc.text"),
        SampleItem(title: "샘플 콘텐츠 · 이번 주 인기 글", body: "실제 서비스 데이터가 아닌 자리표시자입니다.", symbol: "flame"),
        SampleItem(title: "샘플 콘텐츠 · 라이프스타일 팁", body: "레이아웃 미리보기를 위한 목업 텍스트입니다.", symbol: "sparkles"),
        SampleItem(title: "샘플 콘텐츠 · 테크 브리핑", body: "예시용으로 채워 넣은 내용입니다.", symbol: "desktopcomputer"),
        SampleItem(title: "샘플 콘텐츠 · 건강 상식", body: "스크롤 공간 확보를 위한 샘플입니다.", symbol: "heart"),
        SampleItem(title: "샘플 콘텐츠 · 여행 가이드", body: "실제 콘텐츠가 아닌 예시 텍스트입니다.", symbol: "airplane"),
        SampleItem(title: "샘플 콘텐츠 · 오늘의 맛집", body: "자리표시자 예시 콘텐츠입니다.", symbol: "cart"),
        SampleItem(title: "샘플 콘텐츠 · 경제 브리핑", body: "미리보기용 목업 문구입니다.", symbol: "chart.bar"),
        SampleItem(title: "샘플 콘텐츠 · 문화·이벤트", body: "실제 서비스 데이터가 아닙니다.", symbol: "music.note"),
        SampleItem(title: "샘플 콘텐츠 · 스포츠 하이라이트", body: "스크롤 확인용 예시 콘텐츠입니다.", symbol: "flag"),
        SampleItem(title: "샘플 콘텐츠 · 날씨 브리핑", body: "레이아웃 예시를 위한 자리표시자입니다.", symbol: "cloud.sun"),
        SampleItem(title: "샘플 콘텐츠 · 추천 읽을거리", body: "예시용으로 채워 넣은 목업 텍스트입니다.", symbol: "book"),
    ]

    private func makeSampleCard(_ item: SampleItem, tint: UIColor) -> UIView {
        let container = UIView()

        let thumbnail = UIView()
        thumbnail.backgroundColor = tint.withAlphaComponent(0.15)
        thumbnail.layer.cornerRadius = 8
        thumbnail.translatesAutoresizingMaskIntoConstraints = false

        let thumbnailIcon = UIImageView(image: UIImage(systemName: item.symbol) ?? UIImage(systemName: "photo"))
        thumbnailIcon.tintColor = tint
        thumbnailIcon.contentMode = .scaleAspectFit
        thumbnailIcon.translatesAutoresizingMaskIntoConstraints = false
        thumbnail.addSubview(thumbnailIcon)

        let titleLabel = UILabel()
        titleLabel.text = item.title
        titleLabel.font = .systemFont(ofSize: 15, weight: .semibold)
        titleLabel.numberOfLines = 2

        let bodyLabel = UILabel()
        bodyLabel.text = item.body
        bodyLabel.font = .systemFont(ofSize: 13)
        bodyLabel.textColor = .secondaryLabel
        bodyLabel.numberOfLines = 2

        let tagLabel = UILabel()
        tagLabel.text = "예시"
        tagLabel.font = .systemFont(ofSize: 10, weight: .semibold)
        tagLabel.textColor = .tertiaryLabel

        let textStack = UIStackView(arrangedSubviews: [titleLabel, bodyLabel, tagLabel])
        textStack.axis = .vertical
        textStack.spacing = 4
        textStack.translatesAutoresizingMaskIntoConstraints = false

        container.addSubview(thumbnail)
        container.addSubview(textStack)

        NSLayoutConstraint.activate([
            thumbnail.leadingAnchor.constraint(equalTo: container.leadingAnchor, constant: 16),
            thumbnail.topAnchor.constraint(equalTo: container.topAnchor),
            thumbnail.widthAnchor.constraint(equalToConstant: 72),
            thumbnail.heightAnchor.constraint(equalToConstant: 72),
            thumbnail.bottomAnchor.constraint(lessThanOrEqualTo: container.bottomAnchor),

            thumbnailIcon.centerXAnchor.constraint(equalTo: thumbnail.centerXAnchor),
            thumbnailIcon.centerYAnchor.constraint(equalTo: thumbnail.centerYAnchor),
            thumbnailIcon.widthAnchor.constraint(equalToConstant: 30),
            thumbnailIcon.heightAnchor.constraint(equalToConstant: 30),

            textStack.leadingAnchor.constraint(equalTo: thumbnail.trailingAnchor, constant: 12),
            textStack.trailingAnchor.constraint(equalTo: container.trailingAnchor, constant: -16),
            textStack.topAnchor.constraint(equalTo: container.topAnchor, constant: 2),
            textStack.bottomAnchor.constraint(lessThanOrEqualTo: container.bottomAnchor, constant: -2),

            container.heightAnchor.constraint(greaterThanOrEqualToConstant: 80),
        ])
        return container
    }
}

extension FlexAdMidViewController: BuzzFlexDelegate {
    func buzzFlexOnSuccess() {
        flexAdView.bind(buzzFlex)
    }

    func buzzFlexOnFailure(_ error: Error) {
    }

    func buzzFlexOnClicked() {
    }
}
