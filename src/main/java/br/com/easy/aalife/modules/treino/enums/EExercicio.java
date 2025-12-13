package br.com.easy.aalife.modules.treino.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EExercicio {

    SUPINO_RETO("Supino reto"),
    SUPINO_INCLINADO("Supino inclinado"),
    SUPINO_DECLINADO("Supino declinado"),
    CRUCIFIXO("Crucifixo"),
    CRUCIFIXO_INCLINADO("Crucifixo inclinado"),
    CROSSOVER("Crossover"),
    FLEXAO_DE_BRACO("Flexão de braço"),
    PECK_DECK("Peck deck"),

    BARRA_FIXA("Barra fixa"),
    PUXADA_FRONTAL("Puxada frontal"),
    PUXADA_POR_TRAS("Puxada por trás"),
    REMADA_CURVADA("Remada curvada"),
    REMADA_BAIXA("Remada baixa"),
    REMADA_UNILATERAL("Remada unilateral"),
    LEVANTAMENTO_TERRA("Levantamento terra"),
    PULLDOWN("Pulldown"),

    DESENVOLVIMENTO("Desenvolvimento"),
    DESENVOLVIMENTO_HALTERES("Desenvolvimento com halteres"),
    ELEVACAO_LATERAL("Elevação lateral"),
    ELEVACAO_FRONTAL("Elevação frontal"),
    ELEVACAO_POSTERIOR("Elevação posterior"),
    ENCOLHIMENTO("Encolhimento"),

    ROSCA_DIRETA("Rosca direta"),
    ROSCA_ALTERNADA("Rosca alternada"),
    ROSCA_SCOTT("Rosca Scott"),
    ROSCA_CONCENTRADA("Rosca concentrada"),
    ROSCA_MARTELO("Rosca martelo"),
    ROSCA_EM_POLIA("Rosca em polia"),

    TRICEPS_TESTA("Tríceps testa"),
    TRICEPS_CORDA("Tríceps corda"),
    TRICEPS_PULLEY("Tríceps pulley"),
    MERGULHO_NO_BANCO("Mergulho no banco"),
    TRICEPS_FRANCES("Tríceps francês"),

    ROSCA_PUNHO("Rosca de punho"),
    ROSCA_INVERSA("Rosca inversa"),

    ABDOMINAL_TRADICIONAL("Abdominal tradicional"),
    ABDOMINAL_INFRA("Abdominal infra"),
    ABDOMINAL_OBLIQUO("Abdominal oblíquo"),
    PRANCHA("Prancha"),
    ELEVACAO_DE_PERNAS("Elevação de pernas"),
    ABDOMINAL_COM_CARGA("Abdominal com carga"),

    ELEVACAO_PELVICA("Elevação pélvica"),
    GLUTEOS_NO_CABO("Glúteos no cabo"),
    COICE("Coice"),

    AGACHAMENTO_LIVRE("Agachamento livre"),
    AGACHAMENTO_NO_SMITH("Agachamento no Smith"),
    LEG_PRESS("Leg press"),
    EXTENSORA("Cadeira extensora"),
    AFUNDO("Afundo"),
    PASSADA("Passada"),

    MESA_FLEXORA("Mesa flexora"),
    STIFF("Stiff"),
    FLEXORA_EM_PE("Flexora em pé"),

    PANTURRILHA_EM_PE("Panturrilha em pé"),
    PANTURRILHA_SENTADO("Panturrilha sentado"),
    PANTURRILHA_NO_LEG("Panturrilha no leg press"),

    HIPEREXTENSAO("Hiperextensão"),
    BOM_DIA("Bom dia"),

    ESTEIRA("Esteira"),
    BICICLETA("Bicicleta"),
    ELIPTICO("Elíptico"),
    CORDA("Pular corda"),
    REMO_ERGOMETRO("Remo ergômetro"),
    ESCADA("Escada"),

    BURPEE("Burpee"),
    KETTLEBELL_SWING("Kettlebell swing"),
    CLEAN("Clean"),
    SNATCH("Snatch"),
    WALL_BALL("Wall ball"),
    BOX_JUMP("Box jump");

    private final String descricao;
}

