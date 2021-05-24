// *******************************************************************************************
// ** newUserRequest: add new user to platform with organisational unit and password        **
// ** newAccountTypeRequest: add a new account type                                         **
// ** newUnitRequest:add a new unit organisation to the platform                            **
// ** changeUnitRequest: reassign a user to a different unit                                **
// ** editBalanceRequest: edit the current number of credits a unit has                     **
// ** editLimitRequest: edit the current credit limit of a unit                             **
// ** editAssetsOwnedRequest: edit the current number of assets a unit has                  **
// ** newAssetRequest: add a new asset to the platform                                      **
// ** newAssetTypeRequest: add a new asset type to the platform                             **
// ** permissionRequest: change the permission of a user                                    **
// *******************************************************************************************

package tradingPlatform.enumerators;

public enum requestType {
    newUserRequest,
    newAccountTypeRequest,
    newUnitRequest,
    changeUnitRequest,
    editBalanceRequest,
    editLimitRequest,
    editAssetsOwnedRequest,
    newAssetRequest,
    newAssetTypeRequest,
    permissionRequest
}
