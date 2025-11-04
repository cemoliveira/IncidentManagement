package br.univesp.incidentmanagement.domain.incident.enums;

public enum Status {
    AGUARDANDO_ATENDIMENTO("Aguardando atendimento."),  //Estado inicial
    EM_ATENDIMENTO("Em atendimento."),                  //Atendimento iniciado e em andamento
    SOLUCIONADA("Solucionado."),                        //Atendimento finalizado com solução
    ENCERRADA_SEM_SOLUÇÃO("Encerrado sem solução.");    //Atendimento finalizado sem solução

    private final String label;
    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}