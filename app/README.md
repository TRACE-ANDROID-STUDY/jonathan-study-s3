## Q
* Is gson deserialization to java class(from json) necessary? (why not just use JSONObject?)

## Solved

### Annotation
Annotation can be used for 1)invoking compile time error or 2)injecting user defined data into object in runtime. The case for ```@POST``` is under 2).

### Retrofit Response Issue
Below lines only resolves received form-data format while Retrofit script sends data in JSON format.
This was solved by modifying login.php(on apache server on ec2) by adding below.
```
$targetId = $_POST["userId"];
$targetPw = $_POST["userPw"];   //form-data

if($targetId == false || $targetPw == false){
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);     //decode json string into associative array.
    $targetId = data["userId"];
    $targetPw = data["userPw"];
}
```
Description for json_decode:
```json_decode ( string $json [, bool $assoc = FALSE [, int $depth = 512 [, int $options = 0 ]]] ) ```