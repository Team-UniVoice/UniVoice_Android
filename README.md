# 💙 UNIVOICE-Android 💙
### 유니보이스 (UniVoice)
<img src="https://avatars.githubusercontent.com/u/173128955?s=96&v=4" width="300" height="300" /> </br>
> 34st NOW SOPT APP JAM </br>
> 프로젝트 기간 : 2024.06.15 ~ </br>

“다양한 목소리를 통해 더 나은 학생 사회, 더 나은 대학 생활을 만들기 위해서” </br>
```
⏰ 학생회와 학생들의 목소리로 함께 만들어가는 대학 생활 필수 앱, 유니보이스
```
</br>

## 💙Contributors
|<img src="https://github.com/user-attachments/assets/ff4f7095-6eee-4d56-94aa-eaf59771256f" width="250" />|<img src="https://github.com/user-attachments/assets/d44cc116-4591-4458-81db-13c2b57bba96" width="250" />|<img src="https://github.com/user-attachments/assets/9bdc9d59-61a6-4446-a400-1c024a59233f" width="250" />|<img src="https://github.com/user-attachments/assets/c71e02dc-bb8d-4454-92ac-080648bb5f28" width="250" />
|:---------:|:---------:|:---------:|:---------:|
|[👑김언지](https://github.com/Eonji-sw)|[박유진](https://github.com/youjin09222)|[이가을](https://github.com/gaeulzzang)|[임하늘](https://github.com/twogarlic)|
| `메인 홈`</br>`공지사항 등록화면`</br>`자동로그인` | `공지사항 세부화면`</br>`타임피커` | `로그인`</br>`퀵스캔`</br>`저장 홈` | `회원가입`</br>`마이페이지` |
</br>

## 📷 Screenshot
|<img src="https://github.com/user-attachments/assets/e4f576df-565e-47bf-b235-f929e5ea1527" width=70% />|<img src="https://github.com/user-attachments/assets/ffc9104d-3c25-49d9-b2e1-b306ead2405b" width=70% />|<img src="https://github.com/user-attachments/assets/44874438-40f9-445c-885b-65ceda26f16e" width=70% />|
|:---------:|:---------:|:---------:|
|스플래시|초기|로그인|

|<img src="https://github.com/user-attachments/assets/7972a1fc-91c5-45bb-a5e2-31ed65230537" width=70% />|<img src="https://github.com/user-attachments/assets/4eb82928-125b-441e-8275-e65dd75133d9" width=70% />|<img src="https://github.com/user-attachments/assets/f9fa9a04-8b89-409a-aa1e-48bda61719a0" width=70% />|
|:---------:|:---------:|:---------:|
|회원가입|회원가입_개인정보입력|회원가입_학생증 인증|

|<img src="https://github.com/user-attachments/assets/7445659f-67ec-4a23-85e7-f34ae978a13b" width=70% />|<img src="https://github.com/user-attachments/assets/911402c5-8183-48ed-9299-12c733ca38e9" width=70% />|<img src="https://github.com/user-attachments/assets/c0bb8d7a-0ad5-48a0-8f4e-2a0b07dc99c0" width=70% />|
|:---------:|:---------:|:---------:|
|회원가입_계정 생성|회원가입_약관동의|회원가입_학생증 확인|

|<img src="https://github.com/user-attachments/assets/f8bdb6cd-d340-437e-889d-eaf2d8df2c8a" width=70% />|<img src="https://github.com/user-attachments/assets/e457b0a7-0f4c-48e2-8b67-a127f003c2ff" width=70% />|<img src="https://github.com/user-attachments/assets/b6388e26-ce2b-41ea-96fd-de59edf211f9" width=70% />|
|:---------:|:---------:|:---------:|
|메인 홈|퀵스캔|공지사항 세부화면|

|<img src="https://github.com/user-attachments/assets/cdc6954d-09f7-4da7-b84b-b6275a28c41e" width=70% />|<img src="https://github.com/user-attachments/assets/f1b963f8-061e-45ad-91b8-71d3fea9bdd7" width=70% />|<img src="https://github.com/user-attachments/assets/776a864b-31c4-4314-a5b8-ef3967b5b9df" width=70% />|
|:---------:|:---------:|:---------:|
|저장 홈|공지사항 등록화면|마이페이지|

</br>
</br>

## 👩🏻‍💻 Tech Stack
| Title | Content |
| ------------ | -------------------------- |
| Architecture | Clean Architecture, MVVM  |
| Design Pattern | Repository Pattern, Adapter Pattern, Observer Pattern |
| Jetpack Components | AAC Bottom Navigation, ViewPager2, Preference Datastore, Lifecycle, ViewModel, DataBindng  |
| Dependency Injection | Hilt  |
| Network | Retrofit, OkHttp, Multipart  |
| Asynchronous Processing | Coroutine(+ Flow)  |
| Third Party Library | Coil, Timber, kotlinSerialization, CircleIndicator, CircleImageView, Lottie  |
| CI | Github Action(KtLint, Compile Check)  |
| Other Tools | Slack, Notion, Figma, Postman  |\
</br>

## 📁 Foldering
```
📦com.univoice
├─📂app
│  ├─📂di
│  ├─📂interceptor
├─📂core_ui
│  ├─📂base
│  ├─📂component
│  ├─📂theme
│  ├─📂util
│  ├─📂view
├─📂data
│  ├─📂datasource
│  ├─📂dto
│  │  └─📂request
│  │  └─📂response
│  ├─📂mapper
│  ├─📂repositoryimpl
├─📂data_local
├─📂data_remote
│  ├─📂api
│  ├─📂datasourceimpl
├─📂domain
│  ├─📂entity
│  ├─📂repository
├─📂feature
│  ├─📂entry
│  ├─📂home
│  ├─📂login
│  ├─📂noticeDetail
│  ├─📂noticePost
│  ├─📂quickscan
│  ├─📂setting
│  ├─📂signup
│  ├─📂storage
└─📂util
```
</br>

## 💻 Commit Convention
```
- 사용할 커밋 타입은 다음과 같다.
  - ⭐ [FEAT] : 새로운 기능 구현
  - ✅ [MOD] : 코드 수정 및 내부 파일 수정
  - ➕ [ADD] : 부수적인 코드 추가 및 라이브러리 추가, 새로운 파일 생성
  - 🛠️ [CHORE] : 버전 코드 수정, 패키지 구조 [C변경, 타입 및 변수명 변경 등의 작은 변경 등 진짜 별 거 아닌]
  - 🚫 [DEL] : 쓸모없는 코드나 파일 삭제
  - 💟 [UI] : UI 작업 - xml 
  - 🔨 [FIX]: 버그 수정
  - 🚑️ [HOTFIX] : issue나 QA에서 문의된 급한 버그 및 오류 해결
  - 🚚 [MOVE] : 프로젝트 내 파일이나 코드의 이동 (패키지 위치 이동)
  - ⏪️ [RENAME] : 파일 이름 변경
  - ♻️ [REFACTOR]: 코드 리팩토링
  - 📃[DOCS] : README나 WIKI 등의 문서 개정
- 커밋 메시지 예시는 다음과 같다.
    - 예시) `#이슈번호 [FEAT] : 마이페이지 API 연결`
```

</br>

## 📌 Issue Convention
```
- 제목
  [Feat] : color system 구성 (예시)

## 📌𝗧𝗮𝘀𝗸
- [ ] 
- [ ] 

## 💡𝗥𝗲𝗳𝗲𝗿𝗲𝗻𝗰𝗲
```
</br>

## ♻️ PR Convention
```
- 제목
    [Feature/#이슈번호] : color system 구성 (예시)

## ✅ 𝗖𝗵𝗲𝗰𝗸-𝗟𝗶𝘀𝘁
- merge할 브랜치의 위치를 확인해 주세요.(main❌/develop⭕)
- 리뷰가 필요한 경우 리뷰어를 지정해 주세요.
- 리뷰는 PR이 올라오면 최대한 빠르게 진행합니다.
- P1 단계의 리뷰는 빠르게 확인 후 반영합니다.
- Approve된 PR은 assigner가 머지하고, 수정 요청이 온 경우 수정 후 다시 push를 합니다.

## 📌 𝗜𝘀𝘀𝘂𝗲𝘀
- closed #이슈번호

## 📎 𝗪𝗼𝗿𝗸 𝗗𝗲𝘀𝗰𝗿𝗶𝗽𝘁𝗶𝗼𝗻
- 
- 

## 📷 𝗦𝗰𝗿𝗲𝗲𝗻𝘀𝗵𝗼𝘁


## 💬 𝗧𝗼 𝗥𝗲𝘃𝗶𝗲𝘄𝗲𝗿𝘀
```

## ⭐  Code Review Convention
```
## 📌 𝗗𝗲𝘀𝗰𝗿𝗶𝗽𝘁𝗶𝗼𝗻
코드 리뷰 룰은 뱅크샐러드의 Pn 룰을 차용하되, 앱잼 특성에 맞게 사용합니다.
 
**P1: 꼭 반영해주세요 (Request changes)**
⏩ 앱잼 기간 내에 반영
ex) P1: ~~~ 

**P2: 반영해도 좋고 넘어가도 좋습니다 (Approve)**
⏩ 후 순위, 앱잼 기간 후에 반영 (만약 맡은 역할이 모두 끝날 시 반영해도 됨.)
ex) P2: ~~~

**P3: 그 외 질문 등의 소통 (Approve)**
⏩ 질문엔 답변, 그 외는 이모지 답변

## 💡𝗥𝗲𝗳𝗲𝗿𝗲𝗻𝗰𝗲
https://blog.banksalad.com/tech/banksalad-code-review-culture/
```

</br>

</br>
</br>
