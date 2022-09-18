# Repository has been moved because a lot of changes.
https://github.com/minttea25/RobotexKorea


## Robotex

It is a repository to program applications used at Robotex Korea.

This repository does not include the images because of  COPYRIGHTS, but only source codes.


## ***파일 로드 시 주의 할 점***
1. 엑셀 파일(.xls, xlsx) 만 로드 가능합니다.
2. 파일은 다음의 이름들을 가진 시트를 포함하고 있어야 합니다. (이름이 정확히 일치해야 합니다.)
- LegoSumo1kg
- LegoSumo3kg
- LineFollowingE
- LineFollowingJH
- LegoFolkraceE
- LegoFolkraceJH
- RoboLeague
3. 각 데이터는 다음과 같은 형식을 만족해야 합니다. (첨부 엑셀 파일 참조)
no, TeamNumber, TeamName, Belong, Coach, Coach Email, Coach Phone, Member1, Member2, Member3, Member4, Member5
4. 각 시트의 첫째 행은 로드할 데이터에 포함되지 않습니다.
5. 이미 파일이 로드되어 있는 상태에서 다시 로드를 하면 기존 데이터들은 사라지고 리셋됩니다. (로드시 만들어진 엑셀 파일은 남아있습니다.)
6. 'No.' 항목에 값이 있어야 해당 라인의 데이터가 인식이 됩니다.
7. 'No.' 항목에 값이 없는 열이 나오면 해당 라인 직전까지의 데이터만 유효합니다.


## ***프로그램 실행 시 참고할 점***
1. 각각 파일을 로드하는 순간 팀이 편성이 되거나 세계 대회 팀이 정해지며 해당 결과들이 엑셀 파일로 각각 종목별로 저장됩니다.
(저장 위치: .\CreatedFiles\~~~)
2. 다음 팀을 확인할 때는 목록를 클릭하면 다음 팀으로 넘어갑니다.
3. 세계 대회 추첨은 항목을 클릭할 경우 새로운 창이 나오면서 카운트 다운이 시작되고 5 - 4 - 3 - 2 - 1의 순서로 보여진 후 결과가 나타납니다.
4. 세계 대회 추첨 예비 번호는 만들어진 엑셀 파일을 통해 확인 할 수 있습니다.


## ***프로그램 실행이 되지 않을 경우***
1. Java 1.8 버전 이상이 설치되어 있는 지 확인합니다.
2. 다음의 파일들이 exe 실행 프로그램과 같은 위치에 있어야 하며 각 폴더의 내부 파일들도 존재해야합니다. (압축을 푼 상태 그대로 사용하시면 됩니다.)
- lib 폴더
- Setup 폴더
- Images 폴더
