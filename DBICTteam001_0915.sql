insert into store values(seqStore.nextval, 'soon', '���߲�', 3000, 'ġŲ', '����� ���ʱ� ���ʴ�� 82 101ȣ', '070-5558-9656', '���߲� ġŲ', 'chichi.pdf', '465-85-88789', '���� 11:00 ~ ���� 11:00', 'logo_chi.jpg', '��,ȭ', '50');
insert into store values(seqStore.nextval, 'soon', 'Ƽ��̼�ī��', 4500, 'ī��', '���� ���ʱ� ���ʴ�� 100', '02-154-8859', '����, ��, ����, Ŀ��', 'cafeT.pdf', '559-78-66542', '���� 9:00 ~ ���� 7:00', 'logo_cafe.jpg', 'ȭ,��', '40');
insert into store values(seqStore.nextval, 'soon', '�ݿø�����', 500, '����', '���� ���ʱ� ������� 435 1��', '070-2397-8856', '�ݿø��ݳ��� ����', 'halfpizza.pdf', '464-125-11587', '���� 10:30 ~ ���� 9:30', 'logo_half.jpg', '��', '65');

drop sequence seqStore;
create sequence seqStore NOCACHE;
select * from store;
delete from store where stno in (4,5,6);
select count(*) as cnt from store;
commit;

alter table store modify(stno number);