-- 📌 Content (컨텐츠)
CREATE TABLE Content (
  id           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '컨텐츠 id',
  title        VARCHAR(255) NOT NULL COMMENT '컨텐츠 제목',
  description  TEXT         NOT NULL COMMENT '컨텐츠 설명',
  genre        VARCHAR(255) NOT NULL COMMENT '컨텐츠 장르',
  created_at   DATETIME     NOT NULL COMMENT '컨텐츠 생성 날짜',
  updated_at   DATETIME     NOT NULL COMMENT '컨텐츠 수정 날짜',
  thumbnail    VARCHAR(255) NOT NULL COMMENT '컨텐츠 썸네일 URL',
  PRIMARY KEY (id)
);

-- 📌 Episode (에피소드)
CREATE TABLE Episode (
  id             BIGINT       NOT NULL AUTO_INCREMENT COMMENT '에피소드 id',
  content_id     BIGINT       NOT NULL COMMENT '컨텐츠 id (외래키)',
  title          VARCHAR(255) NOT NULL COMMENT '에피소드 제목',
  episode_number INT          NOT NULL COMMENT '에피소드 번호',
  release_date   DATE         NOT NULL COMMENT '에피소드 방영일',
  created_at     DATETIME     NOT NULL COMMENT '에피소드 등록일',
  updated_at     DATETIME     NOT NULL COMMENT '에피소드 수정일',
  thumbnail      VARCHAR(255) NOT NULL COMMENT '에피소드 썸네일 URL',
  PRIMARY KEY (id),
  FOREIGN KEY (content_id) REFERENCES Content (id) ON DELETE CASCADE
);

-- 📌 Guest (비회원)
CREATE TABLE Guest (
  id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '비회원 id',
  session_token VARCHAR(255) NOT NULL UNIQUE COMMENT '세션 토큰',
  created_at    DATETIME     NOT NULL COMMENT '세션 생성 일시',
  PRIMARY KEY (id)
);

-- 📌 Rating (컨텐츠 평가)
CREATE TABLE Rating (
  id         BIGINT                      NOT NULL AUTO_INCREMENT COMMENT '평가 id',
  user_id    BIGINT                      NOT NULL COMMENT '사용자 id (외래키)',
  content_id BIGINT                      NOT NULL COMMENT '컨텐츠 id (외래키)',
  rating     ENUM('LOW','MEDIUM','HIGH') NOT NULL COMMENT '평가등급',
  created_at DATETIME                    NOT NULL COMMENT '평가일자',
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES User (id) ON DELETE CASCADE,
  FOREIGN KEY (content_id) REFERENCES Content (id) ON DELETE CASCADE
);

-- 📌 User (사용자)
CREATE TABLE User (
  id          BIGINT               NOT NULL AUTO_INCREMENT COMMENT '사용자 id',
  username    VARCHAR(255)         NOT NULL UNIQUE COMMENT '사용자 아이디',
  password    VARCHAR(255)         NOT NULL COMMENT '사용자 비밀번호',
  nickname    VARCHAR(255)         NOT NULL UNIQUE COMMENT '사용자 닉네임',
  email       VARCHAR(255)         NOT NULL UNIQUE COMMENT '사용자 이메일',
  profile_img VARCHAR(255)         NOT NULL DEFAULT 'https://storage.cloud.google.com/yangflix-storage/profile_img/profile_image1.jpg' COMMENT '사용자 프로필 이미지 URL',
  role        ENUM('USER','ADMIN') NOT NULL COMMENT '권한 (관리자/사용자)',
  created_at  DATETIME             NOT NULL COMMENT '생성일',
  updated_at  DATETIME             NOT NULL COMMENT '수정일',
  last_login  DATETIME             NULL     COMMENT '마지막 로그인 시간',
  PRIMARY KEY (id)
);

-- 📌 WishlistContent (컨텐츠 찜)
CREATE TABLE WishlistContent (
  id         BIGINT NOT NULL AUTO_INCREMENT COMMENT '컨텐츠 찜 id',
  user_id    BIGINT NOT NULL COMMENT '사용자 id (외래키)',
  content_id BIGINT NOT NULL COMMENT '컨텐츠 id (외래키)',
  created_at DATETIME NOT NULL COMMENT '컨텐츠 찜 날짜',
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES User (id) ON DELETE CASCADE,
  FOREIGN KEY (content_id) REFERENCES Content (id) ON DELETE CASCADE
);

-- 📌 WishlistEpisode (에피소드 찜)
CREATE TABLE WishlistEpisode (
  id         BIGINT   NOT NULL AUTO_INCREMENT COMMENT '에피소드 찜 id',
  user_id    BIGINT   NOT NULL COMMENT '사용자 id (외래키)',
  episode_id BIGINT   NOT NULL COMMENT '에피소드 id (외래키)',
  created_at DATETIME NOT NULL COMMENT '에피소드 찜 날짜',
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES User (id) ON DELETE CASCADE,
  FOREIGN KEY (episode_id) REFERENCES Episode (id) ON DELETE CASCADE
);
