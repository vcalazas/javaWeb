drop database if exists marketplace;
create database marketplace;
use marketplace;

create table pessoa(
	id		int(10)  	not null AUTO_INCREMENT,
	cpf 	varchar(15) not null,
    senha	text(500) not null,
	nome 	varchar(30)	not null,
    genero  int(2) 		not null,
    primary key (id, cpf)
);

create table market(
	id			int(10)  	not null primary key AUTO_INCREMENT,
    nome 		varchar(30)	not null
);

create table venda(
	id			int(10)  	not null primary key AUTO_INCREMENT,
	pessoaId 	int(10) 	not null,
	statusvenda int(2) 		not null,
    datahora 	date 		not null,
    valor 		int(100) 	not null,
    FOREIGN KEY(pessoaId) REFERENCES pessoa(id)
);

create table pagamento(
	id				int(10) not null primary key AUTO_INCREMENT,
	pessoaId 		int(10) not null,
    vendaId 		int(10) not null,
    datahora 		date 	not null,
    valor 			int(100)not null,
    statuspagamento	int(2) 	not null,
    FOREIGN KEY(pessoaId) REFERENCES pessoa(id),
    FOREIGN KEY(vendaId) REFERENCES venda(id)
);

create table produto(
	id				int(10)  		not null primary key AUTO_INCREMENT,
	marketId		int(10)  		not null,
    nome		 	varchar(150) 	not null,
    descricao	 	text(5000) 		not null,
    valorbruto		int(100) 		not null,
    estoque			int(10)			not null,
    FOREIGN KEY(marketId) REFERENCES market(id)
);

create table publicacao(
	id					int(10)  	not null primary key AUTO_INCREMENT,
    marketId			int(10)  	not null,
    datahorainicio 		datetime	not null,
    datahorafim 		datetime	not null,
    valor 				int(100) 	not null,
    titulo	 			varchar(255)not null,
    descricao 			text(5000)  not null,
    sobrepordescricao	tinyint 	not null,
    FOREIGN KEY(marketId) REFERENCES market(id)
);

create table vendapublicacao(
	vendaId 			int(10) 	not null,
    publicacaoId		int(10) 	not null,
    marketId			int(10)  	not null,
    datahorainicio 		datetime	not null,
    datahorafim 		datetime	not null,
    valor 				int(100) 	not null,
    titulo	 			varchar(255)not null,
    descricao 			text(5000)  not null,
    sobrepordescricao	tinyint 	not null,
	FOREIGN KEY(vendaId) REFERENCES venda(id),
    FOREIGN KEY(publicacaoId) REFERENCES publicacao(id)
);

create table produtopublicacao(
	produtoId		int(10) not null,
    publicacaoId	int(10) not null,
    FOREIGN KEY(produtoId) REFERENCES produto(id),
    FOREIGN KEY(publicacaoId) REFERENCES publicacao(id)
);

create table juro(
	id				int(10)  	not null primary key AUTO_INCREMENT,
	marketId		int(10)  	not null,
    publicacaoId 	int(15) 	not null,
    descricao	 	varchar(15) not null,
    valor 			int(100) 	not null,
    tipo 			int(5) 		not null,
    FOREIGN KEY(publicacaoId) REFERENCES publicacao(id),
	FOREIGN KEY(marketId) REFERENCES market(id)
);

DELIMITER $$$  
CREATE PROCEDURE `manterVenda`(
	in vendaid			int(10),
	in vendapessoaId 	int(10),
	in vendastatusvenda int(2),
    in vendadatahora 	date,
    in vendavalor 		int(100)
)  
BEGIN  
	if vendaid is null or vendaid = 0
    then
		insert into venda ( pessoaId, statusvenda, datahora, valor ) values (vendapessoaId, vendastatusvenda, vendadatahora, vendavalor);
	else 
        update venda set pessoaId = vendapessoaId, statusvenda = vendastatusvenda, datahora = vendadatahora, valor = vendavalor where id = vendaid;
    end if;
    commit;
    SELECT max(id) as `ultimoid` from venda;
END$$$  
DELIMITER ;  

DELIMITER $$  
CREATE PROCEDURE `ultimapublicacao`(in mprodutoId int(10), in mpublicacaoId int(10), in mmarketId int(10))  
BEGIN  
	if 
		mprodutoId is not null and
        (
			mpublicacaoId is not null or 
            mmarketId is not null 
		)
	then
		if mpublicacaoid is not null then
			insert into produtopublicacao(produtoId, publicacaoId) values (mprodutoId, mpublicacaoId);
		else 
			insert into produtopublicacao(produtoId, publicacaoId) values (mprodutoId, ( 
				SELECT max(id) as `ultimoid` from publicacao where marketId = mmarketId
			));
		end if;
    end if;
END$$;  
DELIMITER ;  

-- ************************************************ tests ************************************************ --

insert into pessoa (cpf, senha, nome, genero) values ("21576129047","123","Uncowyo", 2);
insert into pessoa (cpf, senha, nome, genero) values ("89643513041","123","Bararion", 1);
insert into pessoa (cpf, senha, nome, genero) values ("64079299036","123","Len", 1);
insert into pessoa (cpf, senha, nome, genero) values ("39266172090","123","Bolgo", 1);
insert into pessoa (cpf, senha, nome, genero) values ("49693846060","123","Daeful", 1);
insert into pessoa (cpf, senha, nome, genero) values ("83519424053","123","Orlas", 1);
insert into pessoa (cpf, senha, nome, genero) values ("76419774055","123","Saellaraen", 2);
insert into pessoa (cpf, senha, nome, genero) values ("32062672004","123","Cyasuwen", 2);
insert into pessoa (cpf, senha, nome, genero) values ("39722445090","123","Slense", 2);
insert into pessoa (cpf, senha, nome, genero) values ("26520784028","123","Miol", 2);

insert into market (nome) values ("Asphalt");
insert into market (nome) values ("SMC - Super Mecha Champions");

insert into produto (marketId, nome, descricao, valorbruto, estoque) values (1, "McLaren 12C Spider", "", 1100000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (1, "Pagani Zonda R", "", 1400000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (1, "Jaguar C-X75", "", 1160000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (1, "Mazzanti Evantra", "", 1200000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (1, "Ford GT 2017", "", 1100000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (2, "Hotsteel", "", 1100000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (2, "Firefox", "", 1100000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (2, "Caramel", "", 1100000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (2, "Skylark", "", 1100000, 10);
insert into produto (marketId, nome, descricao, valorbruto, estoque) values (2, "Arhur", "", 1100000, 10);

insert into publicacao (marketId, datahorainicio, datahorafim, valor, titulo, descricao, sobrepordescricao) values (1, "2011-12-18 13:17:17", "2011-12-18 13:17:17", 100000, "Oferta Exclusiva", "", false);
call ultimapublicacao(1, null, 1);

insert into publicacao (marketId, datahorainicio, datahorafim, valor, titulo, descricao, sobrepordescricao) values (2, "2011-12-18 13:17:17", "2011-12-18 13:17:17", 100000, "SMC - Oferta Exclusiva", "", false);
call ultimapublicacao(6, null, 2);
