insert into store values(seqStore.nextval, 'soon', '맛닭꼬', 3000, '치킨', '서울시 서초구 서초대로 82 101호', '070-5558-9656', '맛닭꼬 치킨', 'chichi.pdf', '465-85-88789', '오전 11:00 ~ 오후 11:00', 'logo_chi.jpg', '월,화', '50');
insert into store values(seqStore.nextval, 'soon', '티라미수카페', 4500, '카페', '서울 서초구 서초대로 100', '02-154-8859', '케익, 빵, 음료, 커피', 'cafeT.pdf', '559-78-66542', '오전 9:00 ~ 오후 7:00', 'logo_cafe.jpg', '화,토', '40');
insert into store values(seqStore.nextval, 'soon', '반올림피자', 500, '피자', '서울 서초구 강남대로 435 1층', '070-2397-8856', '반올림반내림 피자', 'halfpizza.pdf', '464-125-11587', '오전 10:30 ~ 오후 9:30', 'logo_half.jpg', '수', '65');

drop sequence seqStore;
create sequence seqStore NOCACHE;
select * from store;
delete from store where stno in (4,5,6);
select count(*) as cnt from store;
commit;

alter table store modify(stno number);