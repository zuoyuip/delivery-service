-- 安全账户
INSERT INTO TB_USER (USER_ID, USER_PHONE, USER_PASSWORD, USER_IS_VALID, USER_IS_ENABLED,
                     USER_IS_ACCOUNT_NON_EXPIRED, USER_IS_CREDENTIALS_NON_EXPIRED,
                     USER_IS_ACCOUNT_NON_LOCKED, USER_IS_BY_REVIEW, USER_IS_SUBMIT_REVIEW,
                     USER_INFO_ID, REVIEW_ID)
VALUES ('f9cc89243b464feeafd684f4919798d6', '13849069391',
        '{bcrypt}$2a$10$GyWMRSzeoPxXqcqK9VLhFeUVYGYX0m6pT8Qy5CdgEtdfVwpiCbLhu', 1, 1, 1, 1, 1, 0, 0,
        null, null),
       ('8c9d6c47b2bb4d67adaad36230ad91cd', '13203902193',
        '{bcrypt}$2a$10$ZVi9ReHxkkQkIuYcxMwzSeXyN6m2NuAxX4ZQG0JZTeL7QE5BT4VQK', 1, 1, 1, 1, 1, 0, 0,
        null, null),
       ('dbcd2e5bfc424360a825e211202dfc83', '15138862540',
        '{bcrypt}$2a$10$VY9Cw9phvRUX59KCShAAfuKPxRTB.BKD470QvEcNQxWpiBpB9cKP6', 1, 1, 1, 1, 1, 0, 0,
        null, null);

-- 包裹信息
INSERT INTO TB_DELIVERY(DELIVERY_ID, DELIVERY_NAME, DELIVERY_CODE, DELIVERY_USER_NAME,
                        DELIVERY_USER_SEX, DELIVERY_ADDRESS, DELIVERY_GOAL_ADDRESS, DELIVERY_WEIGHT,
                        DELIVERY_REWARD, DELIVERY_REMARK, DELIVERY_DATE, DELIVERY_USER_ID,
                        DELIVERY_DELIVERY_USER_ID, DELIVERY_STATUS)
VALUES ('f9cc89243b464feeafd684f4919798d1', '韵达快递', '598109', '左羽', '男', '宝相寺北一街', '升达经贸管理学院5号宿舍楼',
        '1.00kg', '5.00RMB', '请在傍晚6点之前送达', null,
        'f9cc89243b464feeafd684f4919798d6', 'dbcd2e5bfc424360a825e211202dfc83', 0),
       ('8c9d6c47b2bb4d67ada0d36230ad91cd', '顺丰快递', '554209', '右翼', '女', '宝相寺北二街', '升达经贸管理学院35号宿舍楼',
        '1.00kg', '5.00RMB', '请在傍晚6点之前送达', null,
        '8c9d6c47b2bb4d67adaad36230ad91cd', 'dbcd2e5bfc424360a825e211202dfc83', 0),
       ('dbcd2e5ofc424360a825e211202dfc83', '中通快递', '565109', '小恶龙', '女', '宝相寺北一街', '升达经贸管理学院25号宿舍楼',
        '1.00kg', '5.00RMB', '请在傍晚6点之前送达', null,
        'dbcd2e5bfc424360a825e211202dfc83', 'dbcd2e5bfc424360a825e211202dfc83', 0);