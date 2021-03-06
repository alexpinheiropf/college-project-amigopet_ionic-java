angular.module('amigopet')
  .service('daoUsuario', function($cordovaSQLite, tools) {
    this.salvar = function(conexao, usuario) {
      if (usuario.getId() != 0) {
        var sql = "UPDATE usuario set nome = ?, email = ?, celular = ?, senha = ? where id = ?";
        $cordovaSQLite.execute(conexao, sql, [usuario.getNome(), usuario.getEmail(), usuario.getCelular(),
          usuario.getSenha(), usuario.getId()]).then(function(res) {
          console.log("UPDATE ID -> " + usuario.getId());
        }, function(err) {
          console.error(err);
        });
      } else {
        var sql = "INSERT INTO usuario (nome, email, celular, senha) VALUES ( ? , ? , ? , ? )";
        $cordovaSQLite.execute(conexao, sql, [usuario.getNome(), usuario.getEmail(), usuario.getCelular(),
          usuario.getSenha()]).then(function(res) {
          console.log("INSERT ID -> " + res.insertId);
        }, function(err) {
          console.error(err);
        });
      }
    };

    this.excluir = function(conexao, id) {
      if (id != 0) {
        var sql = "DELETE FROM usuario WHERE ID = ?";
        $cordovaSQLite.execute(conexao, sql, [id]).then(function(res) {
          console.log("Delete ID -> " + id);
        }, function(err) {
          console.error(err);
          console.log("SELECTED -> "
                + res.rows.item(nregistros).id + " "
                + res.rows.item(nregistros).nome + " "
                + res.rows.item(nregistros).email + " "
                + res.rows.item(nregistros).celular + " "
                + res.rows.item(nregistros).senha);
        });
      }
    };

    this.lista = function(conexao, parametro) {
      var resultado = [];
      var parametro = "%" + parametro + "%";
      var sql = "SELECT * FROM usuario where nome like ? or celular like ? order by nome ASC";
      $cordovaSQLite.execute(conexao, sql, [parametro, parametro]).then(function(res) {
        if (res.rows.length > 0) {
          var nregistros = 0;
          var nregistrosFinal = res.rows.length - 1;
          while (nregistros <= nregistrosFinal) {
            console.log("SELECTED -> "
                  + res.rows.item(nregistros).id + " "
                  + res.rows.item(nregistros).nome + " "
                  + res.rows.item(nregistros).email + " "
                  + res.rows.item(nregistros).celular + " "
                  + res.rows.item(nregistros).senha);
            resultado.push({
              id: res.rows[nregistros].id,
              nome: res.rows[nregistros].nome,
              email: res.rows[nregistros].email,
              celular: res.rows[nregistros].celular,
              senha: res.rows[nregistros].senha
            });
            nregistros++;
          }
        }
      }, function(err) {
        console.error(err);
      });
      return resultado;
    };

    this.getObjeto = function(conexao, parametro) {
      var usuario = new Usuario();
      var sql = "SELECT * FROM usuario where id = ?";
      $cordovaSQLite.execute(conexao, sql, [parametro]).then(function(res) {
        if (res.rows.length > 0) {
          var nregistros = 0;
          console.log("SELECTED -> "
                + res.rows.item(nregistros).id + " "
                + res.rows.item(nregistros).nome + " "
                + res.rows.item(nregistros).email + " "
                + res.rows.item(nregistros).celular + " "
                + res.rows.item(nregistros).senha);

          usuario.setId(res.rows[nregistros].id);
          usuario.setNome(res.rows[nregistros].nome);
          usuario.setEmail(res.rows[nregistros].email);
          usuario.setCelular(res.rows[nregistros].celular);
          usuario.setSenha(res.rows[nregistros].senha);
        }
      }, function(err) {
        console.error(err);
      });
      return usuario;
    };


    this.getBranco = function(conexao, parametro) {
      var usuario = new Usuario();
      usuario.setId(0);
      usuario.setNome("");
      usuario.setEmail("");
      usuario.setCelular("");
      usuario.setSenha("");
      return usuario;
    };

  });
