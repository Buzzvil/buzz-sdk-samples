# BuzzAdBenefit SDK for Android

* 개발 가이드: https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/721256746/BuzzAd+Benefit+2.0+Android+SDK

# 2.3.0
* [ADD] 브리지 포인트 조회 화면
* [ADD] CPYoutube / CPK 타잎 지원
* [CHANGE] 로그 정리
* [FIX] 드물게 Fragment 재생성 시 발생하는 크래시 수정

# 2.2.3
* [CHANGE] CPK 지원
* [FIX] 드물게 POP이 크래시(잠시 후 재 실행됨) 되는 문제 수정

# 2.2.2
* [FIX] 드물게 특정 광고에서 이미지가 표시 안되는 문제 수정

# 2.2.1
* [FIX] FeedFragment.init 하위버전 호환

# 2.2.0
* 인터스티셜에 문의하기 버튼 추가
* Video에 Open Measurement 스펙 적용
* Pop 사라지는 시간을 커스텀하는 기능 추가
* Pop 베이스 리워드와 광고 리워드를 통합해서 Preview에 노출
* Pop feed의 툴바 커스텀하는 기능 추가
* 인앱랜딩을 위한 액티비티의 테마가 App 테마를 따르도록 변경.
* 인앱랜딩을 위한 액티비티 커스텀하는 기능 추가
* 액션형 광고의 참여여부에 따른 CTA 변경
* [FIX] 컨텐츠 랜딩 시 Feed 내의 카드뷰로 랜딩되도록 변경.
* [FIX] 특정 단말에서 Pop 종료 버튼(X버튼) 에니메이션 개선

# 2.0.2
* Feed에서 광고 클릭시 가끔 이미지가 다시 로드되면서, 깜빡이는 문제 수정
* 의도치 않게 POP 서비스가 죽었을 때, 다시 살리는 로직 추가
* Benefit-Web을 위한 인터페이스 추가
* AndroidManifest.xml에 android:allowBackup="true" 가 추가되지 않도록 수정

# 2.0.0
* AndroidX 기반으로 변경
