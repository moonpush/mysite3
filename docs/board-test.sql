--insert(새글)
insert
  into board
values ( board_no_seq.nextval, 
		 '첫글입니다.', 
		 '안녕하세요', 
		 4,
		 0, 
		 nvl( ( select max( group_no ) from board ), 0) + 1,
		 1,
		 1,
		 SYSDATE );





-------------------------------------------------
-- 연습
-------------------------------------------------

-- 문장열 더하기 연산자 ||
select * from board;

-- nvl 함수
select nvl( null, 0 ) from dual;

delete from board;
commit;
