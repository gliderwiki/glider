
INSERT INTO `we_user` 
	(`WE_USER_IDX`, 
	`WE_USER_ID`, 
	`WE_USER_NAME`, 
	`WE_USER_NICK`, 
	`WE_USER_KEY`, 
	`WE_USER_PWD`, 
	`WE_USER_JOIN_DATE`, 
	`WE_USER_AUTH_YN`, 
	`WE_USER_AUTH_DATE`
	)
	VALUES
	(1, 
	'test@test.com', 
	'테스트유저', 
	'기본테스트유저', 
	'lcXEywSaKQqRsmxBwUqBasqiBLGRzqZFNLAzNaxwDMGkEtMhoGUwnSVQlaVJwwWgLdRMoEdOmrtGGwRpMdTsnNovVbhrYdPpLCzvFFXateXklcTRpFfQBxhabVilcJAg', -- 암호화키
	'URV48pFKmWw=', -- 1111
	NOW(),
	'Y', 
	NOW()
	);