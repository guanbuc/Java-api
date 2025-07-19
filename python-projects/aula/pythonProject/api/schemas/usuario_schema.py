from api import ma
from ..models import usuario_models
from marshmallow import fields

class UsuarioSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = usuario_models.Usuario
        load_instance = True
        fields = ("id", "nome", "email", "senha", "is_admin", "api_key")

    nome = fields.String(required=True)
    email = fields.String(required=True)
    senha = fields.String(required=True)
    is_admin = fields.Boolean(required=True)
    api_key = fields.String(required=False)