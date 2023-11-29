package com.faesa.app.principal;

import com.faesa.app.controller.EsporteController;
import com.faesa.app.model.Esporte;
import com.faesa.app.util.ScannerUtil;
import com.faesa.app.util.tableBuilder.Column;
import com.faesa.app.util.tableBuilder.TableBuilder;
import com.faesa.app.util.tableBuilder.TextAlign;
import java.util.List;

public class AppEsporte extends App
{
    protected AppEsporte() {
        super("Esporte");
    }
    
    public static Esporte scanEsporte()
    {
    	List<Esporte> esportes = EsporteController.listAll();
        
        // Exibir lista de esportes caso usuário envie em beanco
        TableBuilder tb = new TableBuilder(
            "Esportes",
            new Column("ID", 2, null, TextAlign.RIGHT),
            new Column("Nome", 30)
        );
        
        for(int i = 0; i < esportes.size(); i++) {
        	Esporte e = esportes.get(i);
            tb.newLine()
                .addCell((i + 1) + "")
                .addCell(e.getNome())
                .endLine();
        }
        
        tb.printTable();
        
        while(true)
        {
            String esporte = ScannerUtil.scanString("> Esporte (ID): ", true);

            int index = 0;
            try {
                index = Integer.parseInt(esporte) - 1;
            }
            catch(NumberFormatException e) {
                System.out.println("ID invalido.");
                continue;
            }

            // PESQUISAR POR ID
            try {
	        	Esporte e = esportes.get(index);
	            if(EsporteController.findByUId(e.getUid()) != null)
	                return e;
            }
            catch(IndexOutOfBoundsException e) {
            	System.out.println("ID inexistente");
            	return null;
            }
        }
    }
    
    private void printRegistros(List<Esporte> esportes)
    {
        TableBuilder tb = new TableBuilder(
            "Esportes",
            new Column("ID", 2, null, TextAlign.RIGHT),
            new Column("Evento", 20)
        );
        
        if(esportes == null || esportes.isEmpty())
            tb.newLine()
                .addCell("Nenhum esporte cadastrado", 2)
                .endLine();
        else
            for(Esporte e: esportes)
                tb.newLine()
                    .addCell(e.getUid() + "")
                    .addCell(e.getNome())
                    .endLine();
        
        tb.printTable();
    }

    @Override
    protected String inserirRegistros()
    {
        String nome = ScannerUtil.scanString("> Nome do Esporte: ", 3, 15);

        Esporte e = new Esporte(null, nome, null);
        if(EsporteController.save(e))
            System.out.println("\nEsporte criado com sucesso!");
            
        return "Deseja inserir outro registro?";
    }

    @Override
    protected String removerRegistros()
    {
    	/*
        List<Esporte> esportes = EsporteController.listAll();
        if(esportes == null || esportes.isEmpty()) {
            System.out.println("Ainda não há esportes cadastrados.");
            return null;
        }
        
        printRegistros(esportes);

        System.out.println("\n(Deixe em branco caso queira desistir de remover)");
        Integer id = ScannerUtil.scanIntInRange("> ID do esporte a ser removido: ", 1, null, true);

        if(id == null)
            return null;

        Esporte esporte = null;
        for(Esporte e: esportes)
            if(e.getId().equals(id)) {
                esporte = e;
                break;
            }

        if(esporte == null)
            System.out.println("Não existe nenhum esporte com este ID.");
        else
        {
            if(ScannerUtil.scanString(
                "Deseja mesmo excluir \"" + esporte.getNome() + "\"? (s/N): ",
                false, true, "S", "N"
            ).equalsIgnoreCase("S"))
            {
                if(EsporteController.delete(id))
                    System.out.println("Esporte removido com sucesso.");
                //else
                    //System.out.println("Não existe nenhum esporte com este ID.");
            }
        }
        
        return "Deseja remover outro registro?";
        */
    	
    	Esporte e = scanEsporte();
    	if(e != null) {
	    	if(EsporteController.delete(e.getUid())) {
	    		System.out.println("Esporte removido com sucesso.");
	    	}
	    	else
	    		System.out.println("Houve um problema ao tentar deleter o Esporte");
    	}
    	
        //return "Funcionalidade indisponivel. Digite N para sair: ";
    	return "Deseja remover outro registro?";
    }

    @Override
    protected String atualizarRegistros()
    {
    	/*
        List<Esporte> esportes = EsporteController.listAll();
        if(esportes == null || esportes.isEmpty()) {
            System.out.println("Ainda não há esportes cadastrados.");
            return null;
        }
        
        printRegistros(esportes);
        
        System.out.println("\n(Deixe em branco caso queira desistir de remover)");
        Integer id = ScannerUtil.scanIntInRange("> ID do Evento: ", 1, null, true);
        
        if(id == null)
            return null;

        Esporte e = EsporteController.findById(id);

        if(e == null) {
            System.out.println("Não foi encontrado nenhum esporte com este ID.");
            return "Deseja tentar novamente?";
        }

        System.out.println("(Deixe o campo branco caso queira manter o mesmo valor)");

        e.setNome(ScannerUtil.scanStringOrDefault(
            "> Nome do esporte (" + e.getNome() + ")\n: ", e.getNome())
        );

        if(EsporteController.save(e))
        {
            System.out.println("\nEsporte atualizado com sucesso!");
            System.out.println("- Nome:        " + e.getNome());
            System.out.println("- Inserido em: " + ScannerUtil.formatDate(e.getDtInsert()));
        }
        
        return "Deseja atualizar outro registro?";
        */
        return "Funcionalidade indisponivel. Digite N para sair: ";
    }
    
}
