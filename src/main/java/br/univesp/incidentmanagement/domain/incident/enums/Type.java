package br.univesp.incidentmanagement.domain.incident.enums;

import java.util.Arrays;
import java.util.List;

public enum Type {

    //ADMINISTRATIVA
    ATUALIZAÇÃO_DE_DADOS("Atualização de dados.", Category.ADMINISTRATIVA),
    CANCELAMENTO_DE_MATRÍCULA("Cancelamento de matrícula.", Category.ADMINISTRATIVA),
    SAÍDA_ANTECIPADA("Saída antecipada.", Category.ADMINISTRATIVA),
    SOLICITAÇÃO_DE_DOCUMENTOS("Solicitação de documentos.", Category.ADMINISTRATIVA),
    TRANSFERÊNCIA_DE_CURSO("Transferência de curso.", Category.ADMINISTRATIVA),

    //ASSISTENCIAL
    AFASTAMENTO("Afastamento.", Category.ASSISTENCIAL),
    ATENDIMENTO_AQV("Intervenção do(a) Analista de Qualidade de Vida.", Category.ASSISTENCIAL),
    ATENDIMENTO_COORDENAÇÃO("Intevenção do(a) Coordenador(a).", Category.ASSISTENCIAL),
    ATENDIMENTO_INCLUSÃO("Intevenção do(a) Assistente Técnico de Inclusão.", Category.ASSISTENCIAL),

    //COMUNICAÇÃO
    AGRADECIMENTO("Agradecimento.", Category.COMUNICAÇÃO),
    ELOGIO("Elogio.", Category.COMUNICAÇÃO),
    QUEIXA("Manifestação, Reclamação, Queixa.", Category.COMUNICAÇÃO),
    SUGESTÃO("Sugestão.", Category.COMUNICAÇÃO),

    //DISCIPLINAR
    ADVERTÊNCIA("Advertência.", Category.DISCIPLINAR),
    ATRASO("Atraso.", Category.DISCIPLINAR),
    COMPORTAMENTO("Comportamento inadequado.", Category.DISCIPLINAR),
    CONFLITO_INTERPESSOAL("Conflito interpessoal.", Category.DISCIPLINAR),
    DANO_PATRIMONIAL("Dano ao patrimônio da instituição.", Category.DISCIPLINAR),
    FALTA_DE_CRACHÁ("Falta de crachá.", Category.DISCIPLINAR),
    FALTA_DE_EPI("Falta de EPI.", Category.DISCIPLINAR),
    FALTA_DE_UNIFORME("Falta de uniforme escolar.", Category.DISCIPLINAR),
    USO_INDEVIDO_DE_CELULAR("Uso indevido e não autorizado de celular ou tablet.", Category.DISCIPLINAR),
    USO_INDEVIDO_DE_EQUIPAMENTO_ESCOLAR("Uso indevido ou inadequado de equipamento escolar.", Category.DISCIPLINAR),

    //PEDAGÓGICA
    DESEMPENHO("Desempenho escolar.", Category.PEDAGÓGICA),
    ENTREGA_DE_ATIVIDADES("Entrega de atividades.", Category.PEDAGÓGICA),
    PARTICIPAÇÃO_EM_AULA("Participação em aula.", Category.PEDAGÓGICA),
    RECUPERAÇÃO_DA_APRENDIZAGEM("Recuperação da aprendizagem.", Category.PEDAGÓGICA),
    REGISTRO_PEDAGÓGICO("Registro pedagógico geral (observação do professor).", Category.PEDAGÓGICA),

    //OUTROS
    FURTO("Ocorrência de furto.", Category.OUTROS),
    OUTROS("Tipo de ocorrência não listada.", Category.OUTROS);

    private final String label;
    private final Category category;

    Type(String label, Category category) {
        this.label = label;
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public Category getCategory() {
        return category;
    }

    public static List<Type> getByCategory(Category category) {
        return Arrays.stream(values())
                .filter(t -> t.category == category)
                .toList();
    }
}