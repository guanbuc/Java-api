from ..models import formacao_models
from api import db
from .professor_service import listar_professor_id

def cadastrar_formacao(formacao):
    formacao_bd = formacao_models.Formacao(nome=formacao.nome, descricao=formacao.descricao)

    for i in formacao.professores:
        professor = listar_professor_id(i)
        formacao_bd.professores.append(professor)

    db.session.add(formacao_bd)
    db.session.commit()
    return formacao_bd

def listar_formacoes():
    formacao = formacao_models.Formacao.query.all()
    return formacao

def listar_formacao_id(id):
    formacao  = formacao_models.Formacao.query.filter_by(id=id).first()
    return formacao

def atualiza_formacao(formacao_anterior, formacao_novo):
    formacao_anterior.nome = formacao_novo.nome
    formacao_anterior.descricao = formacao_novo.descricao

    for i in formacao_novo.professores:
        professor = listar_professor_id(i)
        formacao_anterior.professores.append(professor)

    db.session.commit()

def remove_formacao(formacao):
    db.session.delete(formacao)
    db.session.commit()