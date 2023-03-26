# java-chess

체스 미션 저장소

## 체스 규칙 ♟

### 체스 기물
킹, 퀸, 룩, 비숍, 나이트, 폰

### 폰
- 처음 움직일 때 앞으로 최대 2칸 움직일 수 있다.
- 대각선으로 한 칸 움직여 공격한다.
- 평소에는 앞으로 한 칸 움직인다.

### 나이트
- L자로 움직인다. (총 3칸 이동)
- 기물을 뛰어넘을 수 있다.

### 비숍
- 대각선으로만 움직인다. 

### 룩
- 상하좌우로 움직인다.

### 퀸
- 상하좌우, 대각선으로 움직인다.

### 킹
- 상하좌우, 대각선으로 한 칸 움직인다.

## 기능목록 📝

### 기물

- [x] 기물은 움직일 수 있는지 판단한다.
- [x] source 위치부터 target 위치까지 이동 경로를 반환한다.
- [x] 기물은 팀에 속해있어야 한다.
- [x] 기물은 각각 점수로 변환된다.

### 위치
- [x] rank와 file을 가진다.
- [x] rank와 file은 1이상 8이하의 정수여야 한다.
- [x] 위치를 이동한다.

### 팀
- [x] BLACK, WHITE 두 팀이 있다.
- [x] 같은 팀인지 판단한다.

### 체스판
- [x] 기물을 움직인다.
- [x] 특정 팀의 점수를 계산한다.
- [x] King이 존재하는지 확인한다.

### 체스게임
- [x] 턴을 관리한다.
- [x] 게임이 끝났는지 확인한다.
- [x] 모든 팀의 점수를 구한다.
- [x] 우승자를 찾는다.

### 점수
- [x] 점수를 더한다.
- [x] 점수를 비교한다.

## 데이터베이스
### 체스게임 테이블

```sql
create table chess_game (
    chess_game_id int AUTO_INCREMENT NOT NULL,
    turn VARCHAR(40) NOT NULL,
    PRIMARY KEY (chess_game_id)
);
```

- [x] 체스게임을 생성한다.
- [x] 체스게임을 조회한다.
- [x] 체스게임 목록을 조회한다.
- [x] 턴을 수정한다.

### 체스판 테이블

```sql
create table chess_board (
    chess_board_id int AUTO_INCREMENT NOT NULL,
    chess_game_id int NOT NULL,
    piece_type VARCHAR(40) NOT NULL,
    piece_rank int NOT NULL,
    piece_file int NOT NULL,
    team VARCHAR(40) NOT NULL,
    PRIMARY KEY (chess_board_id),
    FOREIGN KEY (chess_game_id) REFERENCES chess_game(chess_game_id) ON DELETE CASCADE
);
```

- [x] 기물의 위치를 저장한다.
- [x] 기물의 위치를 수정한다.

## 게임 진행 시나리오

1. 'start' 를 입력하여 시작한다.
2. 게임 시작 시, 생성되어 있는 체스게임의 아이디를 보여준다.
3. 이어서 실행할 게임의 아이디를 입력한다.
4. 새로 시작하려면 'new' 를 입력한다.
5. 게임을 새로 생성하면 생성된 체스게임 아이디를 출력해준다.
6. 'end' 를 입력하여 종료한다.

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
