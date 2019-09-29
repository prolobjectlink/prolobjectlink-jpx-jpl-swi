:-consult('d:/pas-win32-x64-1.0.0/obj/prolobject.pl').

:-consult('d:/pas-win32-x64-1.0.0/misc/http.pl').

:-consult('d:/pas-win32-x64-1.0.0/misc/integer.pl').

:-consult('d:/pas-win32-x64-1.0.0/web/webpro/model/webpro_address.pl').

webpro_address_new(_) :- 
	render('view/new.html').

webpro_address_edit(ID,ENTITY) :- 
	integer_parse_int(ID, A),
	webpro_address_retrieve_one(A, ENTITY),
	render('view/edit.html').

webpro_address_find_all(LIST) :- 
	webpro_address_retrieve_all(LIST),
	render('view/list.html').

webpro_address_query_all(LIST) :- 
	webpro_address_query_all('select a from WebproAddress a', LIST),
	render('view/list.html').

webpro_address_find_all_range(MAX,FIRST,LIST) :- 
	integer_parse_int(MAX, A),
	integer_parse_int(FIRST, B),
	webpro_address_retrieve_all(A,B,LIST),
	render('view/list.html').

webpro_address_query_all_range(MAX,FIRST,LIST) :- 
	integer_parse_int(MAX, A),
	integer_parse_int(FIRST, B),
	webpro_address_query_all('select a from WebproAddress a',A,B,LIST),
	render('view/list.html').

webpro_address_find(ID,ENTITY) :- 
	integer_parse_int(ID, A),
	webpro_address_retrieve_one(A, ENTITY),
	render('view/show.html').

webpro_address_query(ID,ENTITY) :- 
	atom_concat('select a from WebproAddress a where a.id =', ID, QUERY),
	webpro_address_query_one(QUERY, ENTITY),
	render('view/show.html').

webpro_address_update(ID,STREET,STATE,ZIP,CITY,COUNTRY,ENTITY) :- 
	integer_parse_int(ID, A),
	webpro_address_retrieve_one(A, ENTITY),
	webpro_address_set_street(ENTITY, STREET),
	webpro_address_set_state(ENTITY, STATE),
	webpro_address_set_zip(ENTITY, ZIP),
	webpro_address_set_city(ENTITY, CITY),
	webpro_address_set_country(ENTITY, COUNTRY),
	webpro_address_update(ENTITY),
	render('view/show.html').

webpro_address_create(STREET,STATE,ZIP,CITY,COUNTRY,ENTITY) :- 
	webpro_address(ENTITY),
	webpro_address_set_street(ENTITY, STREET),
	webpro_address_set_state(ENTITY, STATE),
	webpro_address_set_zip(ENTITY, ZIP),
	webpro_address_set_city(ENTITY, CITY),
	webpro_address_set_country(ENTITY, COUNTRY),
	webpro_address_create(ENTITY),
	render('view/show.html').

webpro_address_delete(ID,ENTITY) :- 
	integer_parse_int(ID, A),
	webpro_address_retrieve_one(A, ENTITY),
	webpro_address_delete(ENTITY),
	webpro_address_retrieve_all(ENTITY),
	render('view/list.html').

