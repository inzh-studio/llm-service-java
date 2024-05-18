package inzh.llm.service;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.args.MiroStat;
import de.kherud.llama.args.Sampler;
import java.util.Map;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class InferenceConfiguration {

    private String inputPrefix;
    private String inputSuffix;
    private Boolean cachePrompt;
    private Integer nPredict;
    private Integer topK;
    private Float topP;
    private Float minP;
    private Float tfsZ;
    private Float typicalP;
    private Float temperature;
    private Float dynamicTemperatureRange;
    private Float dynamicTemperatureExponent;
    private Integer repeatLastN;
    private Float repeatPenalty;
    private Float frequencyPenalty;
    private Float presencePenalty;
    private MiroStat miroStat;
    private Float miroStatTau;
    private Float miroStatEta;
    private Boolean penalizeNl;
    private Integer nKeep;
    private Integer seed;
    private Integer nProbs;
    private Integer minKeep;
    private String grammar;
    private String penaltyPrompt;
    private Boolean ignoreEos;
    private Map tokenIdBias;
    private String[] stopStrings;
    private Sampler[] samplers;
    private Boolean stream = Boolean.FALSE;

    public InferenceConfiguration setInputPrefix(String inputPrefix) {
        this.inputPrefix = inputPrefix;
        return this;
    }

    public InferenceConfiguration setInputSuffix(String inputSuffix) {
        this.inputSuffix = inputSuffix;
        return this;
    }

    public InferenceConfiguration setCachePrompt(Boolean cachePrompt) {
        this.cachePrompt = cachePrompt;
        return this;
    }

    public InferenceConfiguration setNPredict(Integer nPredict) {
        this.nPredict = nPredict;
        return this;
    }

    public InferenceConfiguration setTopK(Integer topK) {
        this.topK = topK;
        return this;
    }

    public InferenceConfiguration setTopP(Float topP) {
        this.topP = topP;
        return this;
    }

    public InferenceConfiguration setMinP(Float minP) {
        this.minP = minP;
        return this;
    }

    public InferenceConfiguration setTfsZ(Float tfsZ) {
        this.tfsZ = tfsZ;
        return this;
    }

    public InferenceConfiguration setTypicalP(Float typicalP) {
        this.typicalP = typicalP;
        return this;
    }

    public InferenceConfiguration setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    public InferenceConfiguration setDynamicTemperatureRange(Float dynamicTemperatureRange) {
        this.dynamicTemperatureRange = dynamicTemperatureRange;
        return this;
    }

    public InferenceConfiguration setDynamicTemperatureExponent(Float dynamicTemperatureExponent) {
        this.dynamicTemperatureExponent = dynamicTemperatureExponent;
        return this;
    }

    public InferenceConfiguration setRepeatLastN(Integer repeatLastN) {
        this.repeatLastN = repeatLastN;
        return this;
    }

    public InferenceConfiguration setRepeatPenalty(Float repeatPenalty) {
        this.repeatPenalty = repeatPenalty;
        return this;
    }

    public InferenceConfiguration setFrequencyPenalty(Float frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
        return this;
    }

    public InferenceConfiguration setPresencePenalty(Float presencePenalty) {
        this.presencePenalty = presencePenalty;
        return this;
    }

    public InferenceConfiguration setMiroStat(MiroStat miroStat) {
        this.miroStat = miroStat;
        return this;
    }

    public InferenceConfiguration setMiroStatTau(Float miroStatTau) {
        this.miroStatTau = miroStatTau;
        return this;
    }

    public InferenceConfiguration setMiroStatEta(Float miroStatEta) {
        this.miroStatEta = miroStatEta;
        return this;
    }

    public InferenceConfiguration setPenalizeNl(Boolean penalizeNl) {
        this.penalizeNl = penalizeNl;
        return this;
    }

    public InferenceConfiguration setNKeep(Integer nKeep) {
        this.nKeep = nKeep;
        return this;
    }

    public InferenceConfiguration setSeed(Integer seed) {
        this.seed = seed;
        return this;
    }

    public InferenceConfiguration setNProbs(Integer nProbs) {
        this.nProbs = nProbs;
        return this;
    }

    public InferenceConfiguration setMinKeep(Integer minKeep) {
        this.minKeep = minKeep;
        return this;
    }

    public InferenceConfiguration setGrammar(String grammar) {
        this.grammar = grammar;
        return this;
    }

    public InferenceConfiguration setPenaltyPrompt(String penaltyPrompt) {
        this.penaltyPrompt = penaltyPrompt;
        return this;
    }

    public InferenceConfiguration setIgnoreEos(Boolean ignoreEos) {
        this.ignoreEos = ignoreEos;
        return this;
    }

    public InferenceConfiguration setTokenIdBias(Map tokenIdBias) {
        this.tokenIdBias = tokenIdBias;
        return this;
    }

    public String[] getStopStrings() {
        return stopStrings;
    }

    public InferenceConfiguration setStopStrings(String... stopStrings) {
        this.stopStrings = stopStrings;
        return this;
    }

    public Boolean isStream() {
        return stream;
    }
    
    public InferenceConfiguration setSamplers(Sampler[] samplers) {
        this.samplers = samplers;
        return this;
    }

    public InferenceConfiguration setStream(Boolean stream) {
        this.stream = stream;
        return this;
    }

    public InferenceParameters toParameters() {
        InferenceParameters p = new InferenceParameters();
        if (this.inputPrefix != null) {
            p.setInputPrefix(this.inputPrefix);
        }
        if (this.inputSuffix != null) {
            p.setInputSuffix(this.inputSuffix);
        }
        if (this.cachePrompt != null) {
            p.setCachePrompt(this.cachePrompt);
        }
        if (this.nPredict != null) {
            p.setNPredict(this.nPredict);
        }
        if (this.topK != null) {
            p.setTopK(this.topK);
        }
        if (this.topP != null) {
            p.setTopP(this.topP);
        }
        if (this.minP != null) {
            p.setMinP(this.minP);
        }
        if (this.tfsZ != null) {
            p.setTfsZ(this.tfsZ);
        }
        if (this.typicalP != null) {
            p.setTypicalP(this.typicalP);
        }
        if (this.temperature != null) {
            p.setTemperature(this.temperature);
        }
        if (this.dynamicTemperatureRange != null) {
            p.setDynamicTemperatureRange(this.dynamicTemperatureRange);
        }
        if (this.dynamicTemperatureExponent != null) {
            p.setDynamicTemperatureExponent(this.dynamicTemperatureExponent);
        }
        if (this.repeatLastN != null) {
            p.setRepeatLastN(this.repeatLastN);
        }
        if (this.repeatPenalty != null) {
            p.setRepeatPenalty(this.repeatPenalty);
        }
        if (this.frequencyPenalty != null) {
            p.setFrequencyPenalty(this.frequencyPenalty);
        }
        if (this.presencePenalty != null) {
            p.setPresencePenalty(this.presencePenalty);
        }
        if (this.miroStat != null) {
            p.setMiroStat(this.miroStat);
        }
        if (this.miroStatTau != null) {
            p.setMiroStatTau(this.miroStatTau);
        }
        if (this.miroStatEta != null) {
            p.setMiroStatEta(this.miroStatEta);
        }
        if (this.penalizeNl != null) {
            p.setPenalizeNl(this.penalizeNl);
        }
        if (this.nKeep != null) {
            p.setNKeep(this.nKeep);
        }
        if (this.seed != null) {
            p.setSeed(this.seed);
        }
        if (this.nProbs != null) {
            p.setNProbs(this.nProbs);
        }
        if (this.minKeep != null) {
            p.setMinKeep(this.minKeep);
        }
        if (this.grammar != null) {
            p.setGrammar(this.grammar);
        }
        if (this.penaltyPrompt != null) {
            p.setPenaltyPrompt(this.penaltyPrompt);
        }
        if (this.ignoreEos != null) {
            p.setIgnoreEos(this.ignoreEos);
        }
        if (this.tokenIdBias != null) {
            p.setTokenIdBias(this.tokenIdBias);
        }
        if (this.stopStrings != null) {
            p.setStopStrings(this.stopStrings);
        }
        if (this.samplers != null) {
            p.setSamplers(this.samplers);
        }
        return p;
    }
    
    public InferenceParameters toParameters(String prompt) {
        InferenceParameters parameters = toParameters();
        parameters.setPrompt(prompt);
        return parameters;
    }
    
    public static InferenceConfiguration setDefault(InferenceConfiguration configuration) {
        return configuration
                .setTemperature(0.7f)
                .setPenalizeNl(true)
                .setMiroStat(MiroStat.V2)
                .setStopStrings("[delim]");
    }
    
    public static InferenceConfiguration createDefault() {
        return setDefault(new InferenceConfiguration());
    }
}
