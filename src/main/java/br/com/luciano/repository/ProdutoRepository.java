package br.com.luciano.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.luciano.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
