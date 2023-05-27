
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="css/bootstrap.css" />
  <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="js/bootstrap.js"></script>
    <title>Title</title>
</head>
<body>
<div class="container">
<form>
  <div class="form-group row">
  <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
  </div>
  <select id="inputState" class="form-control">
    <option value="javascript">JavaScript</option>
    <option value="php">PHP</option>
    <option value="java">Java</option>
    <option value="python">Python</option>
    <option value="c#">C#</option>
    <option value="C++">C++</option>
  </select>
  <div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword3">
    </div>
  </div>
  <textarea class="form-control" placeholder="글내용" name="bbsContent" maxlength="2048" style="height:350px;"></textarea>
  <div class="form-group row">
    <div class="mb-3">
      <input class="form-control" type="file" id="formFile">
    </div>
    <div class="col-sm-10">
      <button type="submit" class="btn btn-primary">Sign in</button>
    </div>
  </div>
</form>
</div>
</body>
</html>
