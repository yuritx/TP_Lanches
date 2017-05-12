/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.Date;
import net.casper.data.model.*;

/**
 * Uma fonte de dados em memória para uma coleção de produtos, vendas e itens de venda
 * @author Júlio
 */
public class FonteDeDados {

    private CDataCacheContainer dadosProdutos;
    private CDataCacheContainer dadosVendas;
    private CDataCacheContainer dadosItens;

    public FonteDeDados() throws CDataGridException {
        //Metadados de produto
        String[] nomeColunasProduto = new String[]{"id", "nome", "preco"};
        Class[] tipoColunasProduto = new Class[]{Integer.class, String.class, Double.class};
        String[] chavesPrimariasProduto = new String[]{"id"};
        CRowMetaData metadadosProduto = new CRowMetaData(nomeColunasProduto, tipoColunasProduto, chavesPrimariasProduto);
        dadosProdutos = new CDataCacheContainer("produtos", metadadosProduto);
        dadosProdutos.addData(InicializarProdutos());
        //Metadados de venda
        String[] nomeColunasVenda = new String[]{"id", "data", "total", "desconto"};
        Class[] tipoColunasVenda = new Class[]{Integer.class, Date.class, Double.class, Double.class};
        String[] chavesPrimariasVenda = new String[]{"id"};
        CRowMetaData metadadosVenda = new CRowMetaData(nomeColunasVenda, tipoColunasVenda, nomeColunasVenda);
        dadosVendas = new CDataCacheContainer("vendas", metadadosVenda);
        //dadosVendas.addData(InicializarVendas());
        //Metadados de itens de venda
        String[] nomeColunasItem = new String[]{"idVenda", "idProduto", "quantidade"};
        Class[] tipoColunasItem = new Class[]{Integer.class, Integer.class, Integer.class};
        String[] chavesPrimariasItem = new String[]{"idVenda", "idProduto"};
        CRowMetaData metadadosItem = new CRowMetaData(nomeColunasItem, tipoColunasItem, nomeColunasItem);
        dadosItens = new CDataCacheContainer("itens", metadadosItem);
        //dadosItens.addData(InicializarItens());
    }

    private CDataRow[] InicializarProdutos() throws CDataGridException {
        CDataRow[] linhas = new CDataRow[3];
        linhas[0] = new CDataRow();
        linhas[0].setRawData(new Object[]{1, "Refrigerante", 2.50});
        linhas[1] = new CDataRow();
        linhas[1].setRawData(new Object[]{2, "Sanduíche", 7.90});
        linhas[2] = new CDataRow();
        linhas[2].setRawData(new Object[]{3, "Batata frita", 4.55});
        return linhas;
    }

    private CDataRow[] InicializarVendas() throws CDataGridException {
        CDataRow[] linhas = new CDataRow[1];
        linhas[0] = new CDataRow();
        linhas[0].setRawData(new Object[]{1, new Date(), 2.50, 0.0});
        return linhas;
    }

    private CDataRow[] InicializarItens() throws CDataGridException {
        CDataRow[] linhas = new CDataRow[1];
        linhas[0] = new CDataRow();
        linhas[0].setRawData(new Object[]{1, 1, 1});
        return linhas;
    }

    /**
     * Buscar todos os produtos
     * Cada produto possui "id" (int), "nome" (string), "preco" (double)
     * @return coleção em memória
     * @throws CDataGridException 
     */
    public CDataRowSet buscarProdutos() throws CDataGridException {
        return dadosProdutos.getAll();
    }

    public CDataRowSet buscarProduto(int codigo) throws CDataGridException {
        Object[] parametros = new Object[1];
        parametros[0] = codigo;
        return dadosProdutos.get("id", parametros);
    }

    /**
     * Buscar todas as vendas
     * Cada venda possui "id" (int), "data" (date), "total" (double), "desconto" (double)
     * @return coleção em memória
     * @throws CDataGridException 
     */
    public CDataRowSet buscarVendas() throws CDataGridException {
        return dadosVendas.getAll();
    }
    
    public CDataRowSet buscarVenda(int codigo) throws CDataGridException {
        Object[] parametros = new Object[1];
        parametros[0] = codigo;
        return dadosVendas.get("id", parametros);
    }
    
    /**
     * Buscar itens de uma determinada venda
     * Cada item de venda possui "idVenda" (int), "idProduto" (int), "quantidade" (int)
     * @param codigo o id da venda
     * @return coleção em memória
     * @throws CDataGridException 
     */
    public CDataRowSet buscarItensVenda(int codigo) throws CDataGridException {
        Object[] parametros = new Object[1];
        parametros[0] = codigo;
        return dadosItens.get("idVenda", parametros);        
    }
    
    /**
     * Criar uma nova venda, inicializando o id
     * @return uma nova venda recém criada
     * @throws CDataGridException 
     */
    public int criarVenda(double desconto) throws CDataGridException{
        int id = 1;
        CDataRowSet vendas = dadosVendas.getAll();
        if(vendas.size() > 0){
            int ultimoId = CDataRowSetAggregator.max(vendas, "id").intValue();
            id = ++ultimoId;
        }
        dadosVendas.addSingleRow(new Object[]{id, new Date(), 0.0, desconto});
        return id;
    }
    
    /**
     * Inserir um novo item de venda em uma determinada venda
     * @param idVenda o id da venda
     * @param idProduto o id do produto
     * @param quantidade a quantidade do produto
     * @throws CDataGridException 
     */
    public void inserirItemVenda(int idVenda, int idProduto, int quantidade) throws CDataGridException {
        dadosItens.addSingleRow(new Object[]{idVenda,idProduto,quantidade});
        CDataRowSet produtos = buscarProduto(idProduto);
        produtos.next();
        double valorItem = produtos.getDouble("preco") * quantidade;
        CDataRowSet linhas = buscarVenda(idVenda);
        linhas.next();
        double total = linhas.getDouble("total");
        linhas.setValue("total", total+valorItem);
    }

}
