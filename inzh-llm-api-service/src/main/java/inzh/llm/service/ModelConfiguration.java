package inzh.llm.service;

import de.kherud.llama.ModelParameters;
import de.kherud.llama.args.GpuSplitMode;
import de.kherud.llama.args.LogFormat;
import de.kherud.llama.args.NumaStrategy;
import de.kherud.llama.args.PoolingType;
import de.kherud.llama.args.RopeScalingType;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class ModelConfiguration {

    private Integer seed;
    private Integer nThreads;
    private Integer nThreadsDraft;
    private Integer nThreadsBatch;
    private Integer nThreadsBatchDraft;
    private Integer nPredict;
    private Integer nCtx;
    private Integer nBatch;
    private Integer nUbatch;
    private Integer nKeep;
    private Integer nDraft;
    private Integer nChunks;
    private Integer nParallel;
    private Integer nSequences;
    private Float pSplit;
    private Integer nGpuLayers;
    private Integer nGpuLayersDraft;
    private GpuSplitMode splitMode;
    private Integer mainGpu;
    private float[] tensorSplit;
    private Integer nBeams;
    private Integer grpAttnN;
    private Integer grpAttnW;
    private Float ropeFreqBase;
    private Float ropeFreqScale;
    private Float yarnExtFactor;
    private Float yarnAttnFactor;
    private Float yarnBetaFast;
    private Float yarnBetaSlow;
    private Integer yarnOrigCtx;
    private Float defragmentationThreshold;
    private NumaStrategy numa;
    private RopeScalingType ropeScalingType;
    private PoolingType poolingType;
    private String modelFilePath;
    private String modelDraft;
    private String modelAlias;
    private String modelUrl;
    private String loraBase;
    private Boolean embedding;
    private Boolean continuousBatching;
    private Boolean inputPrefixBos;
    private Boolean ignoreEos;
    private Boolean useMmap;
    private Boolean useMlock;
    private Boolean noKvOffload;
    private String systemPrompt;
    private LogFormat logFormat;

    public ModelConfiguration setSeed(Integer seed) {
        this.seed = seed;
        return this;
    }

    public ModelConfiguration setNThreads(Integer nThreads) {
        this.nThreads = nThreads;
        return this;
    }

    public ModelConfiguration setNThreadsDraft(Integer nThreadsDraft) {
        this.nThreadsDraft = nThreadsDraft;
        return this;
    }

    public ModelConfiguration setNThreadsBatch(Integer nThreadsBatch) {
        this.nThreadsBatch = nThreadsBatch;
        return this;
    }

    public ModelConfiguration setNThreadsBatchDraft(Integer nThreadsBatchDraft) {
        this.nThreadsBatchDraft = nThreadsBatchDraft;
        return this;
    }

    public ModelConfiguration setNPredict(Integer nPredict) {
        this.nPredict = nPredict;
        return this;
    }

    public ModelConfiguration setNCtx(Integer nCtx) {
        this.nCtx = nCtx;
        return this;
    }

    public ModelConfiguration setNBatch(Integer nBatch) {
        this.nBatch = nBatch;
        return this;
    }

    public ModelConfiguration setNUbatch(Integer nUbatch) {
        this.nUbatch = nUbatch;
        return this;
    }

    public ModelConfiguration setNKeep(Integer nKeep) {
        this.nKeep = nKeep;
        return this;
    }

    public ModelConfiguration setNDraft(Integer nDraft) {
        this.nDraft = nDraft;
        return this;
    }

    public ModelConfiguration setNChunks(Integer nChunks) {
        this.nChunks = nChunks;
        return this;
    }

    public ModelConfiguration setNParallel(Integer nParallel) {
        this.nParallel = nParallel;
        return this;
    }

    public ModelConfiguration setNSequences(Integer nSequences) {
        this.nSequences = nSequences;
        return this;
    }

    public ModelConfiguration setPSplit(Float pSplit) {
        this.pSplit = pSplit;
        return this;
    }

    public ModelConfiguration setNGpuLayers(Integer nGpuLayers) {
        this.nGpuLayers = nGpuLayers;
        return this;
    }

    public ModelConfiguration setNGpuLayersDraft(Integer nGpuLayersDraft) {
        this.nGpuLayersDraft = nGpuLayersDraft;
        return this;
    }

    public ModelConfiguration setSplitMode(GpuSplitMode splitMode) {
        this.splitMode = splitMode;
        return this;
    }

    public ModelConfiguration setMainGpu(Integer mainGpu) {
        this.mainGpu = mainGpu;
        return this;
    }

    public ModelConfiguration setTensorSplit(float[] tensorSplit) {
        this.tensorSplit = tensorSplit;
        return this;
    }

    public ModelConfiguration setNBeams(Integer nBeams) {
        this.nBeams = nBeams;
        return this;
    }

    public ModelConfiguration setGrpAttnN(Integer grpAttnN) {
        this.grpAttnN = grpAttnN;
        return this;
    }

    public ModelConfiguration setGrpAttnW(Integer grpAttnW) {
        this.grpAttnW = grpAttnW;
        return this;
    }

    public ModelConfiguration setRopeFreqBase(Float ropeFreqBase) {
        this.ropeFreqBase = ropeFreqBase;
        return this;
    }

    public ModelConfiguration setRopeFreqScale(Float ropeFreqScale) {
        this.ropeFreqScale = ropeFreqScale;
        return this;
    }

    public ModelConfiguration setYarnExtFactor(Float yarnExtFactor) {
        this.yarnExtFactor = yarnExtFactor;
        return this;
    }

    public ModelConfiguration setYarnAttnFactor(Float yarnAttnFactor) {
        this.yarnAttnFactor = yarnAttnFactor;
        return this;
    }

    public ModelConfiguration setYarnBetaFast(Float yarnBetaFast) {
        this.yarnBetaFast = yarnBetaFast;
        return this;
    }

    public ModelConfiguration setYarnBetaSlow(Float yarnBetaSlow) {
        this.yarnBetaSlow = yarnBetaSlow;
        return this;
    }

    public ModelConfiguration setYarnOrigCtx(Integer yarnOrigCtx) {
        this.yarnOrigCtx = yarnOrigCtx;
        return this;
    }

    public ModelConfiguration setDefragmentationThreshold(Float defragmentationThreshold) {
        this.defragmentationThreshold = defragmentationThreshold;
        return this;
    }

    public ModelConfiguration setNuma(NumaStrategy numa) {
        this.numa = numa;
        return this;
    }

    public ModelConfiguration setRopeScalingType(RopeScalingType ropeScalingType) {
        this.ropeScalingType = ropeScalingType;
        return this;
    }

    public ModelConfiguration setPoolingType(PoolingType poolingType) {
        this.poolingType = poolingType;
        return this;
    }

    public ModelConfiguration setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
        return this;
    }

    public ModelConfiguration setModelDraft(String modelDraft) {
        this.modelDraft = modelDraft;
        return this;
    }

    public ModelConfiguration setModelAlias(String modelAlias) {
        this.modelAlias = modelAlias;
        return this;
    }

    public ModelConfiguration setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
        return this;
    }

    public ModelConfiguration setLoraBase(String loraBase) {
        this.loraBase = loraBase;
        return this;
    }

    public ModelConfiguration setEmbedding(Boolean embedding) {
        this.embedding = embedding;
        return this;
    }

    public ModelConfiguration setContinuousBatching(Boolean continuousBatching) {
        this.continuousBatching = continuousBatching;
        return this;
    }

    public ModelConfiguration setInputPrefixBos(Boolean inputPrefixBos) {
        this.inputPrefixBos = inputPrefixBos;
        return this;
    }

    public ModelConfiguration setIgnoreEos(Boolean ignoreEos) {
        this.ignoreEos = ignoreEos;
        return this;
    }

    public ModelConfiguration setUseMmap(Boolean useMmap) {
        this.useMmap = useMmap;
        return this;
    }

    public ModelConfiguration setUseMlock(Boolean useMlock) {
        this.useMlock = useMlock;
        return this;
    }

    public ModelConfiguration setNoKvOffload(Boolean noKvOffload) {
        this.noKvOffload = noKvOffload;
        return this;
    }

    public ModelConfiguration setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
        return this;
    }

    public ModelConfiguration setLogFormat(LogFormat logFormat) {
        this.logFormat = logFormat;
        return this;
    }

    public ModelParameters toParameters() {
        ModelParameters p = new ModelParameters();
        if (this.seed != null) {
            p.setSeed(this.seed);
        }
        if (this.nThreads != null) {
            p.setNThreads(this.nThreads);
        }
        if (this.nThreadsDraft != null) {
            p.setNThreadsDraft(this.nThreadsDraft);
        }
        if (this.nThreadsBatch != null) {
            p.setNThreadsBatch(this.nThreadsBatch);
        }
        if (this.nThreadsBatchDraft != null) {
            p.setNThreadsBatchDraft(this.nThreadsBatchDraft);
        }
        if (this.nPredict != null) {
            p.setNPredict(this.nPredict);
        }
        if (this.nCtx != null) {
            p.setNCtx(this.nCtx);
        }
        if (this.nBatch != null) {
            p.setNBatch(this.nBatch);
        }
        if (this.nUbatch != null) {
            p.setNUbatch(this.nUbatch);
        }
        if (this.nKeep != null) {
            p.setNKeep(this.nKeep);
        }
        if (this.nDraft != null) {
            p.setNDraft(this.nDraft);
        }
        if (this.nChunks != null) {
            p.setNChunks(this.nChunks);
        }
        if (this.nParallel != null) {
            p.setNParallel(this.nParallel);
        }
        if (this.nSequences != null) {
            p.setNSequences(this.nSequences);
        }
        if (this.pSplit != null) {
            p.setPSplit(this.pSplit);
        }
        if (this.nGpuLayers != null) {
            p.setNGpuLayers(this.nGpuLayers);
        }
        if (this.nGpuLayersDraft != null) {
            p.setNGpuLayersDraft(this.nGpuLayersDraft);
        }
        if (this.splitMode != null) {
            p.setSplitMode(this.splitMode);
        }
        if (this.mainGpu != null) {
            p.setMainGpu(this.mainGpu);
        }
        if (this.tensorSplit != null) {
            p.setTensorSplit(this.tensorSplit);
        }
        if (this.nBeams != null) {
            p.setNBeams(this.nBeams);
        }
        if (this.grpAttnN != null) {
            p.setGrpAttnN(this.grpAttnN);
        }
        if (this.grpAttnW != null) {
            p.setGrpAttnW(this.grpAttnW);
        }
        if (this.ropeFreqBase != null) {
            p.setRopeFreqBase(this.ropeFreqBase);
        }
        if (this.ropeFreqScale != null) {
            p.setRopeFreqScale(this.ropeFreqScale);
        }
        if (this.yarnExtFactor != null) {
            p.setYarnExtFactor(this.yarnExtFactor);
        }
        if (this.yarnAttnFactor != null) {
            p.setYarnAttnFactor(this.yarnAttnFactor);
        }
        if (this.yarnBetaFast != null) {
            p.setYarnBetaFast(this.yarnBetaFast);
        }
        if (this.yarnBetaSlow != null) {
            p.setYarnBetaSlow(this.yarnBetaSlow);
        }
        if (this.yarnOrigCtx != null) {
            p.setYarnOrigCtx(this.yarnOrigCtx);
        }
        if (this.defragmentationThreshold != null) {
            p.setDefragmentationThreshold(this.defragmentationThreshold);
        }
        if (this.numa != null) {
            p.setNuma(this.numa);
        }
        if (this.ropeScalingType != null) {
            p.setRopeScalingType(this.ropeScalingType);
        }
        if (this.poolingType != null) {
            p.setPoolingType(this.poolingType);
        }
        if (this.modelFilePath != null) {
            p.setModelFilePath(this.modelFilePath);
        }
        if (this.modelDraft != null) {
            p.setModelDraft(this.modelDraft);
        }
        if (this.modelAlias != null) {
            p.setModelAlias(this.modelAlias);
        }
        if (this.modelUrl != null) {
            p.setModelUrl(this.modelUrl);
        }
        if (this.loraBase != null) {
            p.setLoraBase(this.loraBase);
        }
        if (this.embedding != null) {
            p.setEmbedding(this.embedding);
        }
        if (this.continuousBatching != null) {
            p.setContinuousBatching(this.continuousBatching);
        }
        if (this.inputPrefixBos != null) {
            p.setInputPrefixBos(this.inputPrefixBos);
        }
        if (this.ignoreEos != null) {
            p.setIgnoreEos(this.ignoreEos);
        }
        if (this.useMmap != null) {
            p.setUseMmap(this.useMmap);
        }
        if (this.useMlock != null) {
            p.setUseMlock(this.useMlock);
        }
        if (this.noKvOffload != null) {
            p.setNoKvOffload(this.noKvOffload);
        }
        if (this.systemPrompt != null) {
            p.setSystemPrompt(this.systemPrompt);
        }
        if (this.logFormat != null) {
            p.setLogFormat(this.logFormat);
        }
        return p;
    }

    public ModelParameters toParameters(Configuration configuration) {
        ModelParameters parameters = toParameters();
        if(configuration.getLogPath() != null){
            parameters.setLogFormat(LogFormat.TEXT);
            parameters.setLogDirectory(configuration.getLogPath().toAbsolutePath().toString() + "//");
        }else{
            parameters.setLogFormat(LogFormat.NONE);
        }
        return parameters;
    }
    
    public static ModelConfiguration setDefault(ModelConfiguration configuration){
        return configuration
                .setNCtx(8096)
                .setEmbedding(true)
                .setNThreads(Runtime.getRuntime().availableProcessors())
                .setNGpuLayers(999);
    }
    
    public static ModelConfiguration createDefault() {
        return setDefault(new ModelConfiguration());
    }
}
