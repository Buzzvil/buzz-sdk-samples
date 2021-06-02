
# BuzzScreen SDK for Android
> [English Version Link](README_EN.md)
- [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/384860182/BuzzScreen+SDK) 

## 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](docs/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/3rd_party_licenses.html))"에서 확인할 수 있다.

# 3.27.0
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2014478375/BuzzAd+2.21.x+BuzzScreen+3.24.x) 참조

# 3.25.0
* [UPDATE] targetSdkVersion 30 지원
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2014478375/BuzzAd+2.21.x+BuzzScreen+3.24.x) 참조

# 3.23.0
* [NEW] 잠금화면에서 신규 비디오 사이즈 지원
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1752629414/BuzzAd+2.19.x+BuzzScreen+3.23.x) 참조

# 3.21.0
* [FIX] Analytics 관련 개선
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1739850216/BuzzAd+2.17.x+BuzzScreen+3.21.x) 참조
> ## 3.21.2
> * [FIX] Universal Image Loadner 에서 가끔 IOException 크래시 발생하는 버그 수정

# 3.19.0
* [FIX] 잠금화면 VAST 광고에서 랜딩 후 돌아왔을 때 '더 알아보기'가 동작하지 않는 버그 수정
* [NEW] adfit 연동
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1577680970/BuzzAd+2.15.x+BuzzScreen+3.19.x+2021+2) 참조
> ## 3.19.1
> * [FIX] 통계 관련 오류 수정 (특정 조건에서 통계 누락)

# 3.17.0
* Exoplayer2 업데이트 - 드물게 동영상 광고 재생시 멈추는 현상 수정
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1456177338/BuzzAd+2.13.x+BuzzScreen+3.17.x+2021+1) 참조
> ## 3.17.1
> * [UPDATE] SDK ADN 광고의 배경/택스트 색상 커스터마이징 지원

# 3.15.0
* 특정 퍼블리셔에서 발생한 잠금화면 활성화 문제 관련 방어 코드 추가
> ## 3.15.1
> * [FIX] Android Studio 에서 빌드는 되지만 IDE에서 특정 class 찾지 못하는 문제 수정.

# 3.13.0
* [CHANGE] 이미지 로딩 라이브러리를 UIL 에서 Picasso(v2.71828) 로 변경.
> ## 3.13.1
> * [FIX] 드물게 NPE 발생 - LockCardViewFragment

# 3.11.0
* [FIX] 최초 광고할당 실패시 튜토리얼 오동작 문제 수정
> ## 3.11.1
> * [UPDATE] 2:1 Image support

# 3.9.0
> ## 3.9.1
> * [FIX] 첫번째 광고 할당이 자주 실패되는 문제 수정
> ## 3.9.2
> * [UPDATE] Memory 부족한 상황에서 할당이 실패하는 경우, 재시도를 통해서 할당 가능성 높이기
> ## 3.9.3
> * [FIX] 아랍어 설정시 랜딩 안되는 문제 수정
> ## 3.9.5
> * [FIX] 앱에서 meterial library 1.3.0-alpha02 이상을 사용하는 경우 resource 이름 충돌로 빌드가 실패하는 문제 수정
> ## 3.9.6
> * [UPDATE] 2:1 Image support

# 3.7.0
* [FIX] In App Browser 에서 간헐적인 크래시 수정
> ## 3.7.1
> * [UPDATE] Added Interface for Favorite categories
> * [FIX] Fixed Vertical Video UI
> ## 3.7.2
> * [FIX] 첫번째 광고 할당이 자주 실패되는 문제 수정
> ## 3.7.3
> * [FIX] 아랍어 설정시 랜딩 안되는 문제 수정

# 3.6.0
* [FIX] Lockscreen에서 Content filter메뉴가 screen off 후에도 사라지지 않는 현상 수정
* [FIX] Extension SDKs 1.3.0 배포 (드물게 발생하는 *android.database.CursorIndexOutOfBoundsException 문제 처리”*)
> ## 3.6.1
> * [FIX] 첫번째 광고 할당이 자주 실패되는 문제 수정


# 3.4.2
* [UPDATE] androidX 적용
