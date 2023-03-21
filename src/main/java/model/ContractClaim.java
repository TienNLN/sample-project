package model;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class ContractClaim extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200180d3803806200180d833981810160405260408110156200003757600080fd5b81019080805160405193929190846401000000008211156200005857600080fd5b9083019060208201858111156200006e57600080fd5b82516401000000008111828201881017156200008957600080fd5b82525081516020918201929091019080838360005b83811015620000b85781810151838201526020016200009e565b50505050905090810190601f168015620000e65780820380516001836020036101000a031916815260200191505b50604052602001805160405193929190846401000000008211156200010a57600080fd5b9083019060208201858111156200012057600080fd5b82516401000000008111828201881017156200013b57600080fd5b82525081516020918201929091019080838360005b838110156200016a57818101518382015260200162000150565b50505050905090810190601f168015620001985780820380516001836020036101000a031916815260200191505b50604052505050818181816000620001b5620002ad60201b60201c565b600080546001600160a01b0319166001600160a01b0383169081178255604051929350917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a3506000805460ff60a01b191690556040805160808101909152604f808252620017be60208301398051906020012082805190602001208280519060200120306200024d620002bf60201b60201c565b60001b60405160200180868152602001858152602001848152602001836001600160a01b03168152602001828152602001955050505050506040516020818303038152906040528051906020012060018190555050505050505062000321565b6000620002b9620002c3565b90505b90565b4690565b6000333014156200031c57600080368080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152505050503601516001600160a01b03169150620002bc9050565b503390565b61148d80620003316000396000f3fe608060405234801561001057600080fd5b50600436106100ea5760003560e01c80638456cb591161008c578063bab6e81e11610066578063bab6e81e146103ac578063c003a94f1461041c578063d462e65214610424578063f2fde38b146104ca576100ea565b80638456cb59146103765780638da5cb5b1461037e578063b06030be14610386576100ea565b80633ca8a0d4116100c85780633ca8a0d4146103225780633f4ba83a1461035c5780635c975abb14610366578063715018a61461036e576100ea565b80630c53c51c146100ef57806312e13cca146102285780632d0335ab146102ea575b600080fd5b6101b3600480360360a081101561010557600080fd5b6001600160a01b03823516919081019060408101602082013564010000000081111561013057600080fd5b82018360208201111561014257600080fd5b8035906020019184600183028401116401000000008311171561016457600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550508235935050506020810135906040013560ff166104f0565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101ed5781810151838201526020016101d5565b50505050905090810190601f16801561021a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6102ce6004803603602081101561023e57600080fd5b81019060208101813564010000000081111561025957600080fd5b82018360208201111561026b57600080fd5b8035906020019184600183028401116401000000008311171561028d57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610868945050505050565b604080516001600160a01b039092168252519081900360200190f35b6103106004803603602081101561030057600080fd5b50356001600160a01b031661088e565b60408051918252519081900360200190f35b6103486004803603602081101561033857600080fd5b50356001600160a01b03166108a9565b604080519115158252519081900360200190f35b6103646108ef565b005b61034861096d565b61036461098f565b610364610a5a565b6102ce610ad6565b6103106004803603602081101561039c57600080fd5b50356001600160a01b0316610ae5565b610364600480360360208110156103c257600080fd5b8101906020810181356401000000008111156103dd57600080fd5b8201836020820111156103ef57600080fd5b8035906020019184600183028401116401000000008311171561041157600080fd5b509092509050610af7565b610348610d3b565b6103486004803603602081101561043a57600080fd5b81019060208101813564010000000081111561045557600080fd5b82018360208201111561046757600080fd5b8035906020019184600183028401116401000000008311171561048957600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610d52945050505050565b610364600480360360208110156104e057600080fd5b50356001600160a01b0316610dff565b606060006104fd86610f20565b90506000357fffffffff0000000000000000000000000000000000000000000000000000000090811690821614156105665760405162461bcd60e51b815260040180806020018281038252603d8152602001806113fa603d913960400191505060405180910390fd5b604080516060810182526001600160a01b038916600081815260026020908152908490205483528201529081018790526105a38882888888610f3c565b6105de5760405162461bcd60e51b81526004018080602001828103825260218152602001806114376021913960400191505060405180910390fd5b6001600160a01b038816600090815260026020526040902054610602906001611032565b600260008a6001600160a01b03166001600160a01b0316815260200190815260200160002081905550600080306001600160a01b0316898b6040516020018083805190602001908083835b6020831061066c5780518252601f19909201916020918201910161064d565b6001836020036101000a038019825116818451168082178552505050505050905001826001600160a01b031660601b8152601401925050506040516020818303038152906040526040518082805190602001908083835b602083106106e25780518252601f1990920191602091820191016106c3565b6001836020036101000a0380198251168184511680821785525050505050509050019150506000604051808303816000865af19150503d8060008114610744576040519150601f19603f3d011682016040523d82523d6000602084013e610749565b606091505b5091509150816107a0576040805162461bcd60e51b815260206004820152601c60248201527f46756e6374696f6e2063616c6c206e6f74207375636365737366756c00000000604482015290519081900360640190fd5b7f5845892132946850460bff5a0083f71031bc5bf9aadcd40f1de79423eac9b10b8a338b60405180846001600160a01b03168152602001836001600160a01b0316815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561081f578181015183820152602001610807565b50505050905090810190601f16801561084c5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a19998505050505050505050565b80516020818301810180516004825292820191909301209152546001600160a01b031681565b6001600160a01b031660009081526002602052604090205490565b6001600160a01b0381166000908152600360205260408120548015806108d557506108d2611093565b81105b156108e45760019150506108ea565b60009150505b919050565b6108f76110b1565b6001600160a01b0316610908610ad6565b6001600160a01b031614610963576040805162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015290519081900360640190fd5b61096b6110bb565b565b60005474010000000000000000000000000000000000000000900460ff165b90565b6109976110b1565b6001600160a01b03166109a8610ad6565b6001600160a01b031614610a03576040805162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015290519081900360640190fd5b600080546040516001600160a01b03909116907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a36000805473ffffffffffffffffffffffffffffffffffffffff19169055565b610a626110b1565b6001600160a01b0316610a73610ad6565b6001600160a01b031614610ace576040805162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015290519081900360640190fd5b61096b611182565b6000546001600160a01b031690565b60036020526000908152604090205481565b610aff61096d565b15610b51576040805162461bcd60e51b815260206004820152601060248201527f5061757361626c653a2070617573656400000000000000000000000000000000604482015290519081900360640190fd5b610b59610d3b565b610baa576040805162461bcd60e51b815260206004820152601560248201527f436865636b696e20616c726561647920646f6e652e0000000000000000000000604482015290519081900360640190fd5b610be982828080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250610d5292505050565b610c3a576040805162461bcd60e51b815260206004820152601860248201527f436865636b696e20496420616c726561647920757365642e0000000000000000604482015290519081900360640190fd5b6000610c446110b1565b9050610c4e611093565b60036000836001600160a01b03166001600160a01b03168152602001908152602001600020819055508060048484604051808383808284379190910194855250506040805160209481900385018120805473ffffffffffffffffffffffffffffffffffffffff19166001600160a01b0397881617905594861685524293850184905260609085018181529085018790527f2a5027905e5bec087df40ec0f819f8b617d6b6660dbc2e851c13ad783393b2879486949350889250879160808201848480828437600083820152604051601f909101601f191690920182900397509095505050505050a1505050565b6000610d4d610d486110b1565b6108a9565b905090565b6000806001600160a01b03166004836040518082805190602001908083835b60208310610d905780518252601f199092019160209182019101610d71565b51815160209384036101000a7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff01801990921691161790529201948552506040519384900301909220546001600160a01b0316929092149150610df79050575060006108ea565b506001919050565b610e076110b1565b6001600160a01b0316610e18610ad6565b6001600160a01b031614610e73576040805162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015290519081900360640190fd5b6001600160a01b038116610eb85760405162461bcd60e51b81526004018080602001828103825260268152602001806113d46026913960400191505060405180910390fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a36000805473ffffffffffffffffffffffffffffffffffffffff19166001600160a01b0392909216919091179055565b6000815160001415610f34575060006108ea565b506020015190565b6000806001610f52610f4d88611244565b6112c7565b84878760405160008152602001604052604051808581526020018460ff1681526020018381526020018281526020019450505050506020604051602081039080840390855afa158015610fa9573d6000803e3d6000fd5b5050604051601f1901519150506001600160a01b038116611011576040805162461bcd60e51b815260206004820152601160248201527f496e76616c6964207369676e6174757265000000000000000000000000000000604482015290519081900360640190fd5b866001600160a01b0316816001600160a01b03161491505095945050505050565b60008282018381101561108c576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b60006018603c804204816110a357fe5b04816110ab57fe5b04905090565b6000610d4d61132e565b6110c361096d565b611114576040805162461bcd60e51b815260206004820152601460248201527f5061757361626c653a206e6f7420706175736564000000000000000000000000604482015290519081900360640190fd5b600080547fffffffffffffffffffffff00ffffffffffffffffffffffffffffffffffffffff1690557f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa6111656110b1565b604080516001600160a01b039092168252519081900360200190a1565b61118a61096d565b156111dc576040805162461bcd60e51b815260206004820152601060248201527f5061757361626c653a2070617573656400000000000000000000000000000000604482015290519081900360640190fd5b600080547fffffffffffffffffffffff00ffffffffffffffffffffffffffffffffffffffff16740100000000000000000000000000000000000000001790557f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a2586111656110b1565b600060405180608001604052806043815260200161139160439139805190602001208260000151836020015184604001518051906020012060405160200180858152602001848152602001836001600160a01b03168152602001828152602001945050505050604051602081830303815290604052805190602001209050919050565b60006112d161138a565b8260405160200180807f190100000000000000000000000000000000000000000000000000000000000081525060020183815260200182815260200192505050604051602081830303815290604052805190602001209050919050565b60003330141561138557600080368080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152505050503601516001600160a01b0316915061098c9050565b503390565b6001549056fe4d6574615472616e73616374696f6e2875696e74323536206e6f6e63652c616464726573732066726f6d2c62797465732066756e6374696f6e5369676e6174757265294f776e61626c653a206e6577206f776e657220697320746865207a65726f206164647265737366756e6374696f6e5369676e61747572652063616e206e6f74206265206f6620657865637574654d6574615472616e73616374696f6e206d6574686f645369676e657220616e64207369676e617475726520646f206e6f74206d61746368a2646970667358221220acb7d5f5d5ed0c549153b26d8aede293a933165d82f940251e3b4b6f597ca3d364736f6c63430007060033454950373132446f6d61696e28737472696e67206e616d652c737472696e672076657273696f6e2c6164647265737320766572696679696e67436f6e74726163742c627974657333322073616c742900000000000000000000000000000000000000000000000000000000000000400000000000000000000000000000000000000000000000000000000000000080000000000000000000000000000000000000000000000000000000000000001753746172436865737452657761726473547261636b65720000000000000000000000000000000000000000000000000000000000000000000000000000000003312e300000000000000000000000000000000000000000000000000000000000";

    public static final String FUNC__DAILYTRACKERTIMESTAMPDAYS = "_dailyTrackerTimestampDays";

    public static final String FUNC__DAILYUSERUUID = "_dailyUserUuid";

    public static final String FUNC_checkDailyTimestampValidity = "checkDailyTimestampValidity";

    public static final String FUNC_CHECKDAILYUUIDVALIDITY = "checkDailyUuidValidity";

    public static final String FUNC_DAILYLOG = "dailyLog";

    public static final String FUNC_EXECUTEMETATRANSACTION = "executeMetaTransaction";

    public static final String FUNC_GETNONCE = "getNonce";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final Event DAILYTRACKERCHECKINDONE_EVENT = new Event("DailyTrackerCheckInDone",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }, new TypeReference<Uint256>() {
            }, new TypeReference<Utf8String>() {
            }));
    ;

    public static final Event METATRANSACTIONEXECUTED_EVENT = new Event("MetaTransactionExecuted",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }, new TypeReference<Address>() {
            }, new TypeReference<DynamicBytes>() {
            }));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Address>(true) {
            }));
    ;

    public static final Event PAUSED_EVENT = new Event("Paused",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }));
    ;

    public static final Event UNPAUSED_EVENT = new Event("Unpaused",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
            }));
    ;

    @Deprecated
    protected ContractClaim(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ContractClaim(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ContractClaim(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ContractClaim(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<DailyTrackerCheckInDoneEventResponse> getDailyTrackerCheckInDoneEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DAILYTRACKERCHECKINDONE_EVENT, transactionReceipt);
        ArrayList<DailyTrackerCheckInDoneEventResponse> responses = new ArrayList<DailyTrackerCheckInDoneEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DailyTrackerCheckInDoneEventResponse typedResponse = new DailyTrackerCheckInDoneEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.blockTimestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.uniqueUuid = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DailyTrackerCheckInDoneEventResponse> dailyTrackerCheckInDoneEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DailyTrackerCheckInDoneEventResponse>() {
            @Override
            public DailyTrackerCheckInDoneEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DAILYTRACKERCHECKINDONE_EVENT, log);
                DailyTrackerCheckInDoneEventResponse typedResponse = new DailyTrackerCheckInDoneEventResponse();
                typedResponse.log = log;
                typedResponse.userAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.blockTimestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.uniqueUuid = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DailyTrackerCheckInDoneEventResponse> dailyTrackerCheckInDoneEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DAILYTRACKERCHECKINDONE_EVENT));
        return dailyTrackerCheckInDoneEventFlowable(filter);
    }

    public List<MetaTransactionExecutedEventResponse> getMetaTransactionExecutedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(METATRANSACTIONEXECUTED_EVENT, transactionReceipt);
        ArrayList<MetaTransactionExecutedEventResponse> responses = new ArrayList<MetaTransactionExecutedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MetaTransactionExecutedEventResponse typedResponse = new MetaTransactionExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.relayerAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.functionSignature = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MetaTransactionExecutedEventResponse> metaTransactionExecutedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MetaTransactionExecutedEventResponse>() {
            @Override
            public MetaTransactionExecutedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(METATRANSACTIONEXECUTED_EVENT, log);
                MetaTransactionExecutedEventResponse typedResponse = new MetaTransactionExecutedEventResponse();
                typedResponse.log = log;
                typedResponse.userAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.relayerAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.functionSignature = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MetaTransactionExecutedEventResponse> metaTransactionExecutedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(METATRANSACTIONEXECUTED_EVENT));
        return metaTransactionExecutedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<PausedEventResponse> getPausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PAUSED_EVENT, transactionReceipt);
        ArrayList<PausedEventResponse> responses = new ArrayList<PausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PausedEventResponse typedResponse = new PausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PausedEventResponse>() {
            @Override
            public PausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSED_EVENT, log);
                PausedEventResponse typedResponse = new PausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSED_EVENT));
        return pausedEventFlowable(filter);
    }

    public List<UnpausedEventResponse> getUnpausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UNPAUSED_EVENT, transactionReceipt);
        ArrayList<UnpausedEventResponse> responses = new ArrayList<UnpausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UnpausedEventResponse typedResponse = new UnpausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UnpausedEventResponse>() {
            @Override
            public UnpausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSED_EVENT, log);
                UnpausedEventResponse typedResponse = new UnpausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSED_EVENT));
        return unpausedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> _dailyTrackerTimestampDays(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__DAILYTRACKERTIMESTAMPDAYS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> _dailyUserUuid(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__DAILYUSERUUID,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> checkDailyTimestampValidity(String userAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_checkDailyTimestampValidity,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, userAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> checkDailyTimestampValidity() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_checkDailyTimestampValidity,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> checkDailyUuidValidity(String uniqueUuid) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CHECKDAILYUUIDVALIDITY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uniqueUuid)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> dailyLog(String uniqueUuid) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DAILYLOG,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uniqueUuid)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executeMetaTransaction(String userAddress, byte[] functionSignature, byte[] sigR, byte[] sigS, BigInteger sigV) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EXECUTEMETATRANSACTION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, userAddress),
                        new org.web3j.abi.datatypes.DynamicBytes(functionSignature),
                        new org.web3j.abi.datatypes.generated.Bytes32(sigR),
                        new org.web3j.abi.datatypes.generated.Bytes32(sigS),
                        new org.web3j.abi.datatypes.generated.Uint8(sigV)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getNonce(String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNONCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAUSE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNPAUSE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ContractClaim load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractClaim(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ContractClaim load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractClaim(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ContractClaim load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ContractClaim(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ContractClaim load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ContractClaim(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ContractClaim> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _name, String _version) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name),
                new org.web3j.abi.datatypes.Utf8String(_version)));
        return deployRemoteCall(ContractClaim.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ContractClaim> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _name, String _version) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name),
                new org.web3j.abi.datatypes.Utf8String(_version)));
        return deployRemoteCall(ContractClaim.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ContractClaim> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _name, String _version) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name),
                new org.web3j.abi.datatypes.Utf8String(_version)));
        return deployRemoteCall(ContractClaim.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ContractClaim> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _name, String _version) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name),
                new org.web3j.abi.datatypes.Utf8String(_version)));
        return deployRemoteCall(ContractClaim.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class DailyTrackerCheckInDoneEventResponse extends BaseEventResponse {
        public String userAddress;

        public BigInteger blockTimestamp;

        public String uniqueUuid;
    }

    public static class MetaTransactionExecutedEventResponse extends BaseEventResponse {
        public String userAddress;

        public String relayerAddress;

        public byte[] functionSignature;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PausedEventResponse extends BaseEventResponse {
        public String account;
    }

    public static class UnpausedEventResponse extends BaseEventResponse {
        public String account;
    }
}
